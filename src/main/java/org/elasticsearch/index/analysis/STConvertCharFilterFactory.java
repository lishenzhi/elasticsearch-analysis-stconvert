///*
// * Licensed to Elasticsearch under one or more contributor
// * license agreements. See the NOTICE file distributed with
// * this work for additional information regarding copyright
// * ownership. Elasticsearch licenses this file to you under
// * the Apache License, Version 2.0 (the "License"); you may
// * not use this file except in compliance with the License.
// * You may obtain a copy of the License at
// *
// *    http://www.apache.org/licenses/LICENSE-2.0
// *
// * Unless required by applicable law or agreed to in writing,
// * software distributed under the License is distributed on an
// * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
// * KIND, either express or implied.  See the License for the
// * specific language governing permissions and limitations
// * under the License.
// */

package org.elasticsearch.index.analysis;

import org.elasticsearch.common.inject.Inject;
import org.elasticsearch.common.inject.assistedinject.Assisted;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.index.Index;
import org.elasticsearch.index.settings.IndexSettingsService;

import java.io.Reader;

public class STConvertCharFilterFactory extends AbstractCharFilterFactory {
    private String type="t2s";

    @Inject
    public STConvertCharFilterFactory(Index index, IndexSettingsService indexSettingsService, @Assisted String name, @Assisted Settings settings) {
        super(index, indexSettingsService.getSettings(), name);
        type = settings.get("convert_type", "t2s");
    }

    @Override
    public Reader create(Reader tokenStream) {
        ConvertType convertType=ConvertType.traditional2simple;
        if(type.equals("s2t")){
            convertType = ConvertType.simple2traditional;
        }
        return new STConvertCharFilter(tokenStream,convertType);
    }
}