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

import java.io.StringWriter;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

/**
 *
 * @author Komi Serge Innocent &lt;komi.innocent at gmail.com&gt;
 */
public class YamlItemProviderUtils {

    String item;

    public YamlItemProviderUtils(String item) {
        this.item = item;
    }

    public String getInsertionText() {
        
        String value = item;
        
        StringTokenizer st = new StringTokenizer(value, ".");
        List<String> data = new LinkedList<String>();
        while (st.hasMoreTokens()) {
            String next = st.nextToken();
            System.out.println(next);
            data.add(next);
        }

        int size = data.size();
       
        if (size == 4) {
            return processItem(data, 2);
        } else if (size == 5) {
            return processItem(data, 3);
        }else {
            return processItem(data, 0);
        }
 
    }

    private String processItem(List<String> data, int limit) {

        Iterator<String> iterator = data.iterator();
        int i = 1;
        StringBuilder builder = new StringBuilder();

        builder.append("{");
        while (iterator.hasNext()) {
            String next = iterator.next();
            if (i == 1) {
                builder.append(next)
                       .append(":");
                i++;
                continue;
            } else if (i == (limit + 1) && limit != 0) {
                builder.append("{")
                        .append(data.get(limit))
                        .append(".")
                        .append(data.get(limit+1))
                        .append(":");
                i++;
                break;
            }
            builder.append("{")
                    .append(next)
                    .append(":");
            i++;
        }
        while (i > 1) {
            builder.append("}");
            i--;
        }
        DumperOptions options = new DumperOptions();
        options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        options.setPrettyFlow(true);
        options.setIndent(4);
        Yaml yaml = new Yaml(options);
        Map<String, String> d = (Map<String, String>) yaml.load(builder.toString());

        StringWriter writer = new StringWriter();
        yaml.dump(d, writer);
        String completionValue = writer.toString();

        return completionValue.replace("null", "").trim();
    }
}
