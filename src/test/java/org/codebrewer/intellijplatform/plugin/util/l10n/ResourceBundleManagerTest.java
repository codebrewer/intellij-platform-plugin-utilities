/*
 * Copyright 2020, 2022 Mark Scott
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.codebrewer.intellijplatform.plugin.util.l10n;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ResourceBundle;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ResourceBundleManagerTest {

  @Test
  @DisplayName("getResourceBundle should return expected resource bundle for non-null class")
  public void getResourceBundle_shouldReturnExpectedResourceBundleForNonNullClass() {
    try (final MockedStatic<ResourceBundle> ignored = mockStatic(ResourceBundle.class)) {
      final String objectResourcesName = "java.lang.ObjectResources";
      final ResourceBundle objectResourceBundle = mock(ResourceBundle.class);

      when(ResourceBundle.getBundle(objectResourcesName)).thenReturn(objectResourceBundle);
      assertEquals(objectResourceBundle, ResourceBundleManager.getResourceBundle(Object.class));
      verify(ResourceBundle.class, Mockito.times(1));
      ResourceBundle.getBundle(objectResourcesName);
    }
  }

  @Test
  @DisplayName("getLocalizedString should return expected string for non-null class and non-null key")
  public void getLocalizedString_shouldReturnExpectedStringForNonNullClassAndNonNullKey() {
    try (final MockedStatic<ResourceBundle> ignored = mockStatic(ResourceBundle.class)) {
      final String objectResourcesName = "java.lang.ObjectResources";
      final ResourceBundle objectResourceBundle = mock(ResourceBundle.class);
      final String key = "key";
      final String expectedValue = "hello";

      when(ResourceBundle.getBundle(objectResourcesName)).thenReturn(objectResourceBundle);
      when(objectResourceBundle.getString(key)).thenReturn(expectedValue);
      assertEquals(expectedValue, ResourceBundleManager.getLocalizedString(Object.class, key));
      verify(ResourceBundle.class, Mockito.times(1));
      ResourceBundle.getBundle(objectResourcesName);
    }
  }

  @Test
  @DisplayName("getLocalizedMnemonic should return null character for empty resource value")
  public void getLocalizedMnemonic_shouldReturnNullCharForEmptyResourceValue() {
    try (final MockedStatic<ResourceBundle> ignored = mockStatic(ResourceBundle.class)) {
      final String objectResourcesName = "java.lang.ObjectResources";
      final ResourceBundle objectResourceBundle = mock(ResourceBundle.class);
      final String key = "key";
      final String expectedValue = "";

      when(ResourceBundle.getBundle(objectResourcesName)).thenReturn(objectResourceBundle);
      when(objectResourceBundle.getString(key)).thenReturn(expectedValue);
      assertEquals('\0', ResourceBundleManager.getLocalizedMnemonic(Object.class, key));
      verify(ResourceBundle.class, Mockito.times(1));
      ResourceBundle.getBundle(objectResourcesName);
    }
  }

  @Test
  @DisplayName("getLocalizedMnemonic should return expected character for non-empty resource bundle")
  public void getLocalizedMnemonic_shouldReturnExpectedCharForNonEmptyResourceValue() {
    try (final MockedStatic<ResourceBundle> ignored = mockStatic(ResourceBundle.class)) {
      final String objectResourcesName = "java.lang.ObjectResources";
      final ResourceBundle objectResourceBundle = mock(ResourceBundle.class);
      final String key = "key";
      final String expectedValue = "hello";

      when(ResourceBundle.getBundle(objectResourcesName)).thenReturn(objectResourceBundle);
      when(objectResourceBundle.getString(key)).thenReturn(expectedValue);
      assertEquals(expectedValue.charAt(0),
          ResourceBundleManager.getLocalizedMnemonic(Object.class, key));
      verify(ResourceBundle.class, Mockito.times(1));
      ResourceBundle.getBundle(objectResourcesName);
    }
  }

}
