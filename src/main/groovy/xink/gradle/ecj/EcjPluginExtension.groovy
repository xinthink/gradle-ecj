/*
 * Copyright 2012 yingxinwu.g@gmail.com.
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
package xink.gradle.ecj

/**
 * Extension object for the ECJ Plugin, for getting input from build scripts
 *
 * @author ywu
 */
class EcjPluginExtension {
    String encoding = 'utf-8'

    List warn = DEF_WARNS.clone()

    List err = DEF_ERRS.clone()

    protected static final List DEF_WARNS = [
        'assertIdentifier',
        'charConcat',
        'compareIdentical',
        'constructorName',
        'deadCode',
        'deprecation',
        'discouraged',
        'enumIdentifier',
        'finalBound',
        'finally',
        'forbidden',
        'intfAnnotation',
        'intfNonInherited',
        'maskedCatchBlock',
        'noEffectAssign',
        'pkgDefaultMethod',
        'raw',
        'serial',
        'staticReceiver',
        'suppress',
        'typeHiding',
        'unavoidableGenericProblems',
        'unchecked',
        'unusedImport',
        'unusedLabel',
        'unusedLocal',
        'unusedPrivate',
        'unusedTypeArgs',
        'varargsCast',
        'warningToken',
    ]

    protected static final List DEF_ERRS = []
}
