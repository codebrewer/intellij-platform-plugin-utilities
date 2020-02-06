package org.codebrewer.intellijplatform.plugin.util.l10n;

import static org.junit.Assert.*;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.verifyStatic;
import static org.powermock.api.mockito.PowerMockito.when;

import java.util.ResourceBundle;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest(fullyQualifiedNames = "org.codebrewer.intellijplatform.plugin.util.l10n.*")
public class ResourceBundleManagerTest {
  @Before
  public void setUp() {
    mockStatic(ResourceBundle.class);
  }

  @Test
  public void getResourceBundle_shouldReturnExpectedResourceBundleForNonNullClass() {
    final String objectResourcesName = "java.lang.ObjectResources";
    final ResourceBundle objectResourceBundle = mock(ResourceBundle.class);

    when(ResourceBundle.getBundle(objectResourcesName)).thenReturn(objectResourceBundle);
    assertEquals(objectResourceBundle, ResourceBundleManager.getResourceBundle(Object.class));
    verifyStatic(ResourceBundle.class, Mockito.times(1));
    ResourceBundle.getBundle(objectResourcesName);
  }

  @Test
  public void getLocalizedString_shouldReturnExpectedStringForNonNullClassAndNonNullKey() {
    final String objectResourcesName = "java.lang.ObjectResources";
    final ResourceBundle objectResourceBundle = mock(ResourceBundle.class);
    final String key = "key";
    final String expectedValue = "hello";

    when(ResourceBundle.getBundle(objectResourcesName)).thenReturn(objectResourceBundle);
    when(objectResourceBundle.getString(key)).thenReturn(expectedValue);
    assertEquals(expectedValue, ResourceBundleManager.getLocalizedString(Object.class, key));
    verifyStatic(ResourceBundle.class, Mockito.times(1));
    ResourceBundle.getBundle(objectResourcesName);
  }

  @Test
  public void getLocalizedMnemonic_shouldReturnNullCharForEmptyResourceValue() {
    final String objectResourcesName = "java.lang.ObjectResources";
    final ResourceBundle objectResourceBundle = mock(ResourceBundle.class);
    final String key = "key";
    final String expectedValue = "";

    when(ResourceBundle.getBundle(objectResourcesName)).thenReturn(objectResourceBundle);
    when(objectResourceBundle.getString(key)).thenReturn(expectedValue);
    assertEquals('\0', ResourceBundleManager.getLocalizedMnemonic(Object.class, key));
    verifyStatic(ResourceBundle.class, Mockito.times(1));
    ResourceBundle.getBundle(objectResourcesName);
  }

  @Test
  public void getLocalizedMnemonic_shouldReturnExpectedCharForNonEmptyResourceValue() {
    final String objectResourcesName = "java.lang.ObjectResources";
    final ResourceBundle objectResourceBundle = mock(ResourceBundle.class);
    final String key = "key";
    final String expectedValue = "hello";

    when(ResourceBundle.getBundle(objectResourcesName)).thenReturn(objectResourceBundle);
    when(objectResourceBundle.getString(key)).thenReturn(expectedValue);
    assertEquals(expectedValue.charAt(0),
        ResourceBundleManager.getLocalizedMnemonic(Object.class, key));
    verifyStatic(ResourceBundle.class, Mockito.times(1));
    ResourceBundle.getBundle(objectResourcesName);
  }
}
