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

package com.centonni.springboot.configuration.tool.yaml;

import com.centonni.springboot.configuration.tool.common.AbstractSpringBootConfigurationCompletionProvider;
import com.centonni.springboot.configuration.tool.common.DefaultSpringBootConfigurationCompletionItem;
import com.centonni.springboot.configuration.tool.common.SpringBootConfigurationType;
import org.netbeans.api.editor.mimelookup.MimeRegistration;
import org.netbeans.api.java.classpath.ClassPath;
import org.netbeans.spi.editor.completion.CompletionItem;
import org.netbeans.spi.editor.completion.CompletionProvider;
import org.springframework.boot.configurationprocessor.metadata.ItemMetadata;

/**
 * The Spring Boot Yaml Configuration implementation of CompletionProvider.
 *
 * The entry point of completion support. This provider is registered for
 * text/x-yaml files and is enabled if spring-boot is available on the
 * classpath.
 * see {@link AbstractSpringBootConfigurationCompletionProvider}
 *
 * @author Komi Serge Innocent &lt;komi.innocent at gmail.com&gt;
 */
@MimeRegistration(mimeType = "text/x-yaml", service = CompletionProvider.class)
public class SpringBootYamlConfigurationCompletionProvider extends AbstractSpringBootConfigurationCompletionProvider{

    @Override
    public CompletionItem getCompletionItem(ItemMetadata configurationItem, ClassPath classPath, int dotOffset, int caretOffset) {
        return new DefaultSpringBootConfigurationCompletionItem(configurationItem, classPath, dotOffset, caretOffset,SpringBootConfigurationType.YAML);
    }

}
