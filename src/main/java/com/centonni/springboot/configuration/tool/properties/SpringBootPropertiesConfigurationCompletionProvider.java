/*
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package com.centonni.springboot.configuration.tool.properties;

import com.centonni.springboot.configuration.tool.common.AbstractSpringBootConfigurationCompletionProvider;
import com.centonni.springboot.configuration.tool.common.DefaultSpringBootConfigurationCompletionItem;
import com.centonni.springboot.configuration.tool.common.SpringBootConfigurationType;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Element;
import javax.swing.text.JTextComponent;
import javax.swing.text.StyledDocument;
import org.netbeans.api.editor.mimelookup.MimeRegistration;
import org.netbeans.api.java.classpath.ClassPath;
import org.netbeans.spi.editor.completion.CompletionItem;
import org.netbeans.spi.editor.completion.CompletionProvider;
import org.netbeans.spi.editor.completion.CompletionResultSet;
import org.netbeans.spi.editor.completion.CompletionTask;
import org.netbeans.spi.editor.completion.support.AsyncCompletionQuery;
import org.netbeans.spi.editor.completion.support.AsyncCompletionTask;
import org.openide.filesystems.FileObject;
import org.openide.util.Exceptions;
import org.openide.windows.Mode;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;
import org.springframework.boot.configurationprocessor.metadata.ConfigurationMetadata;
import org.springframework.boot.configurationprocessor.metadata.ItemMetadata;
import org.springframework.boot.configurationprocessor.metadata.JsonMarshaller;

/**
 * The Spring Boot Configuration implementation of CompletionProvider.
 *
 * The entry point of completion support. This provider is registered for
 * text/x-properties files and is enabled if spring-boot is available on the
 * classpath.
 * see {@link AbstractSpringBootConfigurationCompletionProvider}
 * 
 * @author Komi Serge Innocent &lt;komi.innocent at gmail.com&gt;
 */
@MimeRegistration(mimeType = "text/x-properties", service = CompletionProvider.class)
public class SpringBootPropertiesConfigurationCompletionProvider extends AbstractSpringBootConfigurationCompletionProvider {

    @Override
    public CompletionItem getCompletionItem(ItemMetadata configurationItem, ClassPath classPath, int dotOffset, int caretOffset) {
        return new DefaultSpringBootConfigurationCompletionItem(configurationItem, classPath, dotOffset, caretOffset,SpringBootConfigurationType.PROPERTIES);
    }
 
}
