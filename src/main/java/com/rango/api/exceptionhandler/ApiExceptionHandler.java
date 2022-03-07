package com.rango.api.exceptionhandler;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.PropertyBindingException;
import com.rango.domain.exception.EntidadeNaoEncontradaException;
import com.rango.domain.exception.NegocioException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
// ResponseEntityExceptionHandler é uma implementação que trata exceptions internas padrões do spring mvc
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    public static final String MSG_ERRO_GENERICA_USUARIO_FINAL = "Ocorreu um erro interno inesperado no sistema. Tente novamente e se "
            + "o problema persistir, entre em contato com o administrador do sistema.";

    public static final String MSG_RECURSO_NAO_ENCONTRADO = "Recurso não encontrado";

    public static final String MSG_ERRO_NEGOCIO = "Violação de regra de negócio";

    public static final String MSG_INCOMPREENSIVEL = "Mensagem incompreensível";

    public static final String MSG_PARAMETRO_INVALIDO = "Parâmetro inválido";

    public static final String MSG_ERRO_SISTEMA = "Erro de sistema";

    public static final String MSG_DADOS_NVALIDOS = "Dados inválidos";

    @Autowired
    private MessageSource messageSource;

    // Erro quando ocorre erro relacionado as informações passadas nas entidades
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handleValidationInternal(ex, ex.getBindingResult(), headers, status, request);
    }

    private ResponseEntity<Object> handleValidationInternal(Exception ex, BindingResult bindingResult, HttpHeaders headers,
                                                            HttpStatus status, WebRequest request) {
        String detail = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.";

        List<Problema.Objeto> problemaObjetos = bindingResult.getAllErrors().stream()
                .map(objetoError -> {
                    String mensagem = messageSource.getMessage(objetoError, LocaleContextHolder.getLocale());

                    String nome = objetoError.getObjectName();

                    if (objetoError instanceof FieldError) {
                        nome = ((FieldError) objetoError).getField();
                    }

                    return Problema.Objeto.builder()
                            .nome(nome)
                            .mensagemUsuario(mensagem)
                            .build();
                })
                .collect(Collectors.toList());

        Problema problema = createProblemBuilder(status, detail, MSG_DADOS_NVALIDOS)
                .mensagemUsuario(detail)
                .objetos(problemaObjetos)
                .build();

        return handleExceptionInternal(ex, problema, headers, status, request);
    }

    // Trata Exceptions genéricas não tratadas
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleUncaught(Exception ex, WebRequest request) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        String detalhe = "Ocorreu um erro interno inesperado no sistema. "
                + "Tente novamente e se o problema persistir, entre em contato "
                + "com o administrador do sistema.";

        ex.printStackTrace();

        Problema problema = createProblemBuilder(status, detalhe, MSG_ERRO_SISTEMA)
                .mensagemUsuario(detalhe)
                .build();

        return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
    }

    // Erro quando consulta um endpoint inexistente
    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex,
                                                                   HttpHeaders headers, HttpStatus status, WebRequest request) {

        String detalhe = String.format("O recurso %s, que você tentou acessar, é inexistente.",
                ex.getRequestURL());

        Problema problema = createProblemBuilder(status, detalhe, MSG_RECURSO_NAO_ENCONTRADO)
                .mensagemUsuario(detalhe)
                .build();

        return handleExceptionInternal(ex, problema, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
                                                        HttpStatus status, WebRequest request) {

        if (ex instanceof MethodArgumentTypeMismatchException) {
            return handleMethodArgumentTypeMismatch(
                    (MethodArgumentTypeMismatchException) ex, headers, status, request);
        }

        return super.handleTypeMismatch(ex, headers, status, request);
    }

    // Erro quando o tipo do parametro na URL é inválido
    private ResponseEntity<Object> handleMethodArgumentTypeMismatch(
            MethodArgumentTypeMismatchException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {

        String detalhe = String.format("O parâmetro de URL '%s' recebeu o valor '%s', "
                        + "que é de um tipo inválido. Corrija e informe um valor compatível com o tipo %s.",
                ex.getName(), ex.getValue(), ex.getRequiredType().getSimpleName());

        Problema problema = createProblemBuilder(status, detalhe, MSG_PARAMETRO_INVALIDO)
                .mensagemUsuario(detalhe)
                .build();

        return handleExceptionInternal(ex, problema, headers, status, request);
    }

    // Erro de sintaxe no json que foi enviado
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {

        Throwable rootCause = ExceptionUtils.getRootCause(ex);

        if (rootCause instanceof InvalidFormatException) {
            return handleInvalidFormat((InvalidFormatException) rootCause, headers, status, request);
        } else if (rootCause instanceof PropertyBindingException) {
            return handlePropertyBinding((PropertyBindingException) rootCause, headers, status, request);
        }

        String detalhe = "O corpo da requisição está inválido. Verifique erro de sintaxe.";

        Problema problema = createProblemBuilder(status, detalhe, MSG_INCOMPREENSIVEL)
                .mensagemUsuario(detalhe)
                .build();

        return handleExceptionInternal(ex, problema, headers, status, request);
    }

    private ResponseEntity<Object> handlePropertyBinding(PropertyBindingException ex,
                                                         HttpHeaders headers, HttpStatus status, WebRequest request) {

        // Criei o método joinPath para reaproveitar em todos os métodos que precisam
        // concatenar os nomes das propriedades (separando por ".")
        String path = joinPath(ex.getPath());

        String detalhe = String.format("A propriedade '%s' não existe. "
                + "Corrija ou remova essa propriedade e tente novamente.", path);

        Problema problema = createProblemBuilder(status, detalhe, MSG_INCOMPREENSIVEL)
                .mensagemUsuario(MSG_ERRO_GENERICA_USUARIO_FINAL)
                .build();

        return handleExceptionInternal(ex, problema, headers, status, request);
    }

    // Erro ao enviar ao enviar tipo incorreto para o campo
    private ResponseEntity<Object> handleInvalidFormat(InvalidFormatException ex,
                                                       HttpHeaders headers, HttpStatus status, WebRequest request) {

        String path = joinPath(ex.getPath());

        String detalhe = String.format("A propriedade '%s' recebeu o valor '%s', "
                        + "que é de um tipo inválido. Corrija e informe um valor compatível com o tipo %s.",
                path, ex.getValue(), ex.getTargetType().getSimpleName());

        Problema problema = createProblemBuilder(status, detalhe, MSG_INCOMPREENSIVEL)
                .mensagemUsuario(MSG_ERRO_GENERICA_USUARIO_FINAL)
                .build();

        return handleExceptionInternal(ex, problema, headers, status, request);
    }

    // Erro para entidades que não são encontradas
    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity<?> handleEntidadeNaoEncontrada(
            EntidadeNaoEncontradaException ex, WebRequest request) {

        HttpStatus status = HttpStatus.NOT_FOUND;
        String detalhe = ex.getMessage();

        Problema problema = createProblemBuilder(status, detalhe, MSG_RECURSO_NAO_ENCONTRADO)
                .mensagemUsuario(detalhe)
                .build();

        return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
    }

    // Erros mais genéricos de negócio
    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<?> tratarNegocio(NegocioException ex, WebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String detalhe = ex.getMessage();

        Problema problema = createProblemBuilder(status, detalhe, MSG_ERRO_NEGOCIO)
                .mensagemUsuario(detalhe)
                .build();

        return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
    }

    // Estamos sobrescrevendo esse método da classe ResponseEntityExceptionHandler, pois as
    // exceptions genéricas chamam esse método que por padrão alguns vem com o corpo null
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
                                                             HttpStatus status, WebRequest request) {

        if (body == null) {
            body = Problema.builder().timestamp(OffsetDateTime.now()).titulo(status.getReasonPhrase())
                    .status(status.value()).mensagemUsuario(MSG_ERRO_GENERICA_USUARIO_FINAL).build();
        } else if (body instanceof String) {
            body = Problema.builder().timestamp(OffsetDateTime.now()).titulo((String) body).status(status.value())
                    .mensagemUsuario(MSG_ERRO_GENERICA_USUARIO_FINAL).build();
        }

        return super.handleExceptionInternal(ex, body, headers, status, request);
    }

    private Problema.ProblemaBuilder createProblemBuilder(HttpStatus status, String detalhe, String titulo) {

        return Problema.builder().timestamp(OffsetDateTime.now()).status(status.value())
                .titulo(titulo).detalhe(detalhe);
    }

    private String joinPath(List<JsonMappingException.Reference> references) {
        return references.stream()
                .map(JsonMappingException.Reference::getFieldName)
                .collect(Collectors.joining("."));
    }
}
