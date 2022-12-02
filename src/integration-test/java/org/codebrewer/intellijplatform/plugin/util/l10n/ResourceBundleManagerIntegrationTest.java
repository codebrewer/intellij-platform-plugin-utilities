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
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.MissingResourceException;
import java.util.Objects;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("integration-test")
public class ResourceBundleManagerIntegrationTest {
  @Test
  @DisplayName("getResourceBundle should throw NullPointerException for null class")
  public void getResourceBundle_shouldThrowNullPointerExceptionForNullClass() {
    assertThrows(
        NullPointerException.class, () -> ResourceBundleManager.getResourceBundle(null));
  }

  @Test
  @DisplayName("getResourceBundle should throw MissingResourceException when bundle not present")
  public void getResourceBundle_shouldThrowMissingResourceExceptionWhenBundleNotPresent() {
    assertThrows(
        MissingResourceException.class, () -> ResourceBundleManager.getResourceBundle(String.class));
  }

  @Test
  @DisplayName("getResourceBundle should return resource bundle when present for non-null class")
  public void getResourceBundle_shouldReturnResourceBundleWhenPresentForNonNullClass() {
    assertNotNull(ResourceBundleManager.getResourceBundle(Object.class));
  }

  @Test
  @DisplayName("getLocalizedString should throw NullPointerException for null class")
  public void getLocalizedString_shouldThrowNullPointerExceptionForNullClass() {
    assertThrows(
        NullPointerException.class, () -> ResourceBundleManager.getLocalizedString(null, ""));
  }

  @Test
  @DisplayName("getLocalizedString should throw NullPointerException for null key")
  public void getLocalizedString_shouldThrowNullPointerExceptionForNullKey() {
    assertThrows(
        NullPointerException.class, () -> ResourceBundleManager.getLocalizedString(Object.class, null));
  }

  @Test
  @DisplayName("getLocalizedString should throw MissingResourceException when key not present")
  public void getLocalizedString_shouldThrowMissingResourceExceptionWhenKeyNotPresent() {
    assertThrows(
        MissingResourceException.class, () -> ResourceBundleManager.getLocalizedString(Objects.class, "missing"));
  }

  @Test
  @DisplayName("getLocalizedString should return expected string for non-null class and non-null key")
  public void getLocalizedString_shouldReturnExpectedStringForNonNullClassAndNonNullKey() {
    final String key = "foo.text";
    final String expectedValue = "Save file";

    assertEquals(expectedValue, ResourceBundleManager.getLocalizedString(Object.class, key));
  }

  @Test
  @DisplayName("getLocalizedMnemonic should throw NullPointerException for null class")
  public void getLocalizedMnemonic_shouldThrowNullPointerExceptionForNullClass() {
    assertThrows(
        NullPointerException.class, () -> ResourceBundleManager.getLocalizedMnemonic(null, ""));
  }

  @Test
  @DisplayName("getLocalizedMnemonic should throw NullPointerException for null key")
  public void getLocalizedMnemonic_shouldThrowNullPointerExceptionForNullKey() {
    assertThrows(
        NullPointerException.class, () -> ResourceBundleManager.getLocalizedMnemonic(Object.class, null));
  }

  @Test
  @DisplayName("getLocalizedMnemonic should return null character for empty resource value")
  public void getLocalizedMnemonic_shouldReturnNullCharForEmptyResourceValue() {
    final String key = "bar.text.mnemonic";

    assertEquals('\0', ResourceBundleManager.getLocalizedMnemonic(Object.class, key));
  }

  @Test
  @DisplayName("getLocalizedMnemonic should return expected character for non-empty resource value")
  public void getLocalizedMnemonic_shouldReturnExpectedCharForNonEmptyResourceValue() {
    final String key = "foo.text.mnemonic";
    final char expectedValue = 'S';

    assertEquals(expectedValue, ResourceBundleManager.getLocalizedMnemonic(Object.class, key));
  }
}
