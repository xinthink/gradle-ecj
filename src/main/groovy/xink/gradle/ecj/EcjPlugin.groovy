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

import static xink.gradle.ecj.EcjPluginExtension.*

import org.gradle.api.*
import org.gradle.api.tasks.compile.Compile

/**
 * Bring Eclipse JDT core batch compiler to gradle builds
 *
 * @author ywu
 */
class EcjPlugin implements Plugin<Project> {

    private static final ARTIFACT_ECJ = 'org.eclipse.jdt.core.compiler:ecj:3.7.1'
    private static final ECJ_MAIN_CLS = 'org.eclipse.jdt.internal.compiler.batch.Main'

    private logger

    /**
     * {@inheritDoc}
     */
    @Override
    void apply(final Project project) {
        this.logger = project.logger

        project.plugins.apply org.gradle.api.plugins.JavaPlugin

        project.configurations.add 'ecj'
        project.dependencies.add 'ecj', ARTIFACT_ECJ
        project.extensions.create 'ecj', EcjPluginExtension

        configCompiler project
    }

    private configCompiler = { project->

        project.tasks.withType(Compile) {
            doFirst {
                // compiler args
                def compilerArgs = [
                    '-source', sourceCompatibility,
                    '-target', targetCompatibility,
                ]

                if (project.ecj.encoding) compilerArgs << '-encoding' << project.ecj.encoding

                configCompileFlags project.ecj.warn, DEF_WARNS, compilerArgs, '-warn:' // warning options
                configCompileFlags project.ecj.err, DEF_ERRS, compilerArgs, '-err:' // warnings should be converted to errors

                // tell ant to use ecj in a forked process
                logger.info "invoking ecj $compilerArgs"
                options.fork executable: 'java', jvmArgs: [ '-cp', project.configurations.ecj.asPath, ECJ_MAIN_CLS ]
                options.define compilerArgs: compilerArgs
            }
        }
    }

    private configCompileFlags = { flags, defaultFlags, output, prefix->

        def flagList = flags
        if (!flagList) {
            flagList = defaultFlags
        }

        if (flagList) {
            output << prefix + flagList.join(',')
        }
    }
}
