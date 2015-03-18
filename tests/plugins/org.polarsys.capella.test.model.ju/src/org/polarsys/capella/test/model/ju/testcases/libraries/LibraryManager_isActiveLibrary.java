/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.model.ju.testcases.libraries;

import java.util.Arrays;
import java.util.List;

import org.polarsys.capella.core.libraries.model.CapellaModel;
import org.polarsys.capella.test.framework.api.BasicTestCase;

/**
 * @author Erwan Brottier
 */
public class LibraryManager_isActiveLibrary extends BasicTestCase {

  @SuppressWarnings("nls")
  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList("libraries/MonProjet1", "libraries/MaLibrairie1", "libraries/MaLibrairie2");
  }

  @SuppressWarnings("nls")
  @Override
  public void test() {
    // -- SCENARIO -- //
    CapellaModel monProjet1 = getTestModel("libraries/MonProjet1");
    CapellaModel maLibrairie1 = (CapellaModel) getTestModel("libraries/MaLibrairie1");
    CapellaModel maLibrairie2 = (CapellaModel) getTestModel("libraries/MaLibrairie2");
    // -- ORACLE -- //
    maLibrairie2.setActive(monProjet1, false);
    assertFalse(maLibrairie2.isActive(monProjet1));
    maLibrairie2.setActive(maLibrairie1, false);
    assertFalse(maLibrairie2.isActive(maLibrairie1));
    maLibrairie2.setActive(monProjet1, true);
    assertTrue(maLibrairie2.isActive(monProjet1));
    assertFalse(maLibrairie2.isActive(maLibrairie1));
  }
}
