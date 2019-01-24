/*
 * Copyright (C) 2013-2017 NTT DATA Corporation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.terasoluna.gfw.web.token.transaction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;

import org.springframework.web.servlet.tags.form.AbstractHtmlElementTag;
import org.springframework.web.servlet.tags.form.TagWriter;

/**
 * Ability to embed transaction token information as meta tag in tag head}
 */
public class TransactionMetaTag extends AbstractHtmlElementTag {

    private static final long serialVersionUID = 1L;

    /**
     * Renders the transaction token string in meta tag with {@code name} and {@code content} attributes <br>
     * The value of {@code name} attribute is {@code _TRANSACTION_TOKEN_INFO}. {@code content} attribute is the transaction
     * token string <br>
     * @see org.springframework.web.servlet.tags.form.AbstractFormTag#writeTagContent(org.springframework.web.servlet.tags.form.TagWriter)
     */
    @Override
    protected int writeTagContent(
            final TagWriter tagWriter) throws JspException {
        HttpServletRequest request = (HttpServletRequest) pageContext
                .getRequest();

        TransactionToken nextToken = (TransactionToken) request.getAttribute(
                TransactionTokenInterceptor.NEXT_TOKEN_REQUEST_ATTRIBUTE_NAME);
        if (nextToken != null) {
            tagWriter.startTag("meta");
            tagWriter.writeAttribute("name",
                    TransactionTokenInterceptor.TOKEN_REQUEST_PARAMETER);
            tagWriter.writeAttribute("content", nextToken.getTokenString());
            tagWriter.endTag();
        }
        return SKIP_BODY;
    }

}
