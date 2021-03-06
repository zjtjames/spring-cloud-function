/*
 * Copyright 2012-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.cloud.function.compiler.java;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Holder for the results of compilation. If compilation was successful the set of classes
 * that resulted from compilation will be available. If compilation was not successful the
 * error messages should provide information about why. Note that compilation may succeed
 * and yet there will still be informational or warning messages collected.
 *
 * @author Andy Clement
 * @author Mark Fisher
 */
public class CompilationResult {

	List<CompilationMessage> compilationMessages = new ArrayList<>();

	List<Class<?>> compiledClasses = new ArrayList<>();

	private boolean successfulCompilation;

	private Map<String, byte[]> classBytes = new HashMap<>();

	private List<File> resolvedAdditionalDependencies = new ArrayList<>();

	public CompilationResult(boolean successfulCompilation) {
		this.successfulCompilation = successfulCompilation;
	}

	public void addClassBytes(String name, byte[] bytes) {
		this.classBytes.put(name, bytes);
	}

	public List<File> getResolvedAdditionalDependencies() {
		return this.resolvedAdditionalDependencies;
	}

	public void setResolvedAdditionalDependencies(
			List<File> resolvedAdditionalDependencies) {
		this.resolvedAdditionalDependencies = resolvedAdditionalDependencies;
	}

	public byte[] getClassBytes(String classname) {
		return this.classBytes.get(classname);
	}

	public boolean wasSuccessful() {
		return this.successfulCompilation;
	}

	public List<Class<?>> getCompiledClasses() {
		return this.compiledClasses;
	}

	public void setCompiledClasses(List<Class<?>> compiledClasses) {
		this.compiledClasses = compiledClasses;
	}

	public List<CompilationMessage> getCompilationMessages() {
		return Collections.unmodifiableList(this.compilationMessages);
	}

	public void recordCompilationMessage(CompilationMessage message) {
		this.compilationMessages.add(message);
	}

	public void recordCompilationMessages(List<CompilationMessage> messages) {
		this.compilationMessages.addAll(messages);
	}

	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append("Compilation result: #classes=" + this.compiledClasses.size()
				+ "  #messages=" + this.compilationMessages.size() + "\n");
		s.append("Compiled classes:\n").append(this.compiledClasses).append("\n");
		s.append("Compilation messages:\n").append(this.compilationMessages).append("\n");
		return s.toString();
	}

}
