/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.devtools.j2objc.ast;

import com.google.devtools.j2objc.javac.BindingConverter;
import com.google.devtools.j2objc.types.Types;

import org.eclipse.jdt.core.dom.ITypeBinding;

import javax.lang.model.type.TypeMirror;

/**
 * Node type for character literal values.
 */
public class CharacterLiteral extends Expression {

  private char charValue = '\0';
  private final ITypeBinding typeBinding;
  private final TypeMirror typeMirror;

  public CharacterLiteral(org.eclipse.jdt.core.dom.CharacterLiteral jdtNode) {
    super(jdtNode);
    charValue = jdtNode.charValue();
    typeBinding = BindingConverter.wrapBinding(jdtNode.resolveTypeBinding());
    typeMirror = BindingConverter.getType(typeBinding);
  }

  public CharacterLiteral(CharacterLiteral other) {
    super(other);
    charValue = other.charValue();
    typeBinding = other.getTypeBinding();
    typeMirror = other.getTypeMirror();
  }

  public CharacterLiteral(char charValue, Types typeEnv) {
    this.charValue = charValue;
    typeBinding = typeEnv.resolveJavaType("char");
    typeMirror = BindingConverter.getType(typeBinding);
  }

  @Override
  public Kind getKind() {
    return Kind.CHARACTER_LITERAL;
  }

  @Override
  public ITypeBinding getTypeBinding() {
    return typeBinding;
  }

  @Override
  public TypeMirror getTypeMirror() {
    return typeMirror;
  }

  public char charValue() {
    return charValue;
  }

  @Override
  protected void acceptInner(TreeVisitor visitor) {
    visitor.visit(this);
    visitor.endVisit(this);
  }

  @Override
  public CharacterLiteral copy() {
    return new CharacterLiteral(this);
  }
}
