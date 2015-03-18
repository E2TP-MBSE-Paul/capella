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
import java.util.Collection;
import java.util.List;

import org.polarsys.capella.common.libraries.IModel;
import org.polarsys.capella.common.libraries.manager.LibraryManagerExt;
import org.polarsys.capella.core.libraries.model.CapellaModel;
import org.polarsys.capella.test.framework.api.BasicTestCase;

/**
 * @author Erwan Brottier
 */
public class LibraryManager_setLibraryActiveState extends BasicTestCase {

  @SuppressWarnings("nls")
  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList("libraries/MonProjet1", "libraries/MaLibrairie1", "libraries/MaLibrairie2", "libraries/MaLibrairie3");
  }

  @SuppressWarnings("nls")
  @Override
  public void test() {
    // -- SCENARIO -- //
    CapellaModel monProjet1 = getTestModel("libraries/MonProjet1");
    CapellaModel maLibrairie1 = (CapellaModel) getTestModel("libraries/MaLibrairie1");
    CapellaModel maLibrairie2 = (CapellaModel) getTestModel("libraries/MaLibrairie2");
    CapellaModel maLibrairie3 = (CapellaModel) getTestModel("libraries/MaLibrairie3");
    monProjet1.addReference(maLibrairie1);
    maLibrairie1.addReference(maLibrairie2);
    maLibrairie2.addReference(maLibrairie3);
    maLibrairie1.setActive(monProjet1, true);
    maLibrairie2.setActive(monProjet1, false);
    maLibrairie3.setActive(monProjet1, true);
    maLibrairie2.setActive(maLibrairie1, true);
    maLibrairie3.setActive(maLibrairie1, false);
    // -- ORACLE -- //
    Collection<IModel> libs = LibraryManagerExt.getAllActivesReferences(monProjet1);
    assertTrue(libs.size() == 2);
    assertTrue(libs.contains(maLibrairie1));
    assertFalse(libs.contains(maLibrairie2));
    assertTrue(libs.contains(maLibrairie3));
    libs = LibraryManagerExt.getAllActivesReferences(maLibrairie1);
    assertTrue(libs.size() == 1);
    assertTrue(libs.contains(maLibrairie2));
    assertFalse(libs.contains(maLibrairie3));
  }
}
