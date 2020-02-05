package org.codebrewer.intellijplatform.plugin.util.l10n;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.MissingResourceException;
import java.util.Objects;
import org.codebrewer.test.IntegrationTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(IntegrationTest.class)
public class ResourceBundleManagerIntegrationTest {
  @Test(expected = NullPointerException.class)
  public void getResourceBundle_shouldThrowNullPointerExceptionForNullClass() {
    ResourceBundleManager.getResourceBundle(null);
  }

  @Test(expected = MissingResourceException.class)
  public void getResourceBundle_shouldThrowMissingResourceExceptionWhenBundleNotPresent() {
    ResourceBundleManager.getResourceBundle(String.class);
  }

  @Test
  public void getResourceBundle_shouldReturnResourceBundleWhenPresentForNonNullClass() {
    assertNotNull(ResourceBundleManager.getResourceBundle(Object.class));
  }

  @Test(expected = NullPointerException.class)
  public void getLocalizedString_shouldThrowNullPointerExceptionForNullClass() {
    ResourceBundleManager.getLocalizedString(null, "");
  }

  @Test(expected = NullPointerException.class)
  public void getLocalizedString_shouldThrowNullPointerExceptionForNullKey() {
    ResourceBundleManager.getLocalizedString(Object.class, null);
  }

  @Test(expected = MissingResourceException.class)
  public void getLocalizedString_shouldThrowMissingResourceExceptionWhenKeyNotPresent() {
    ResourceBundleManager.getLocalizedString(Objects.class, "missing");
  }

  @Test
  public void getLocalizedString_shouldReturnExpectedStringForNonNullClassAndNonNullKey() {
    final String key = "foo.text";
    final String expectedValue = "Save file";

    assertEquals(expectedValue, ResourceBundleManager.getLocalizedString(Object.class, key));
  }

  @Test(expected = NullPointerException.class)
  public void getLocalizedMnemonic_shouldThrowNullPointerExceptionForNullClass() {
    ResourceBundleManager.getLocalizedMnemonic(null, "");
  }

  @Test(expected = NullPointerException.class)
  public void getLocalizedMnemonic_shouldThrowNullPointerExceptionForNullKey() {
    ResourceBundleManager.getLocalizedMnemonic(Object.class, null);
  }

  @Test
  public void getLocalizedMnemonic_shouldReturnNullCharForEmptyResourceValue() {
    final String key = "bar.text.mnemonic";

    assertEquals('\0', ResourceBundleManager.getLocalizedMnemonic(Object.class, key));
  }

  @Test
  public void getLocalizedMnemonic_shouldReturnExpectedCharForNonEmptyResourceValue() {
    final String key = "foo.text.mnemonic";
    final char expectedValue = 'S';

    assertEquals(expectedValue, ResourceBundleManager.getLocalizedMnemonic(Object.class, key));
  }
}
