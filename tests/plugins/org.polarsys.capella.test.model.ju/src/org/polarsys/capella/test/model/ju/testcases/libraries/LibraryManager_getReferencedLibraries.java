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
import org.polarsys.capella.core.libraries.model.CapellaModel;
import org.polarsys.capella.test.framework.api.BasicTestCase;

/**
 * @author Erwan Brottier
 */
public class LibraryManager_getReferencedLibraries extends BasicTestCase {

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
    CapellaModel maLibrairie1 = getTestModel("libraries/MaLibrairie1");
    CapellaModel maLibrairie2 = getTestModel("libraries/MaLibrairie2");
    monProjet1.addReference(maLibrairie1);
    monProjet1.addReference(maLibrairie2);
    // -- ORACLE -- //
    Collection<IModel> libs = monProjet1.getAvailableReferences();
    assertTrue(libs.size() == 2);
    assertTrue(libs.contains(maLibrairie1));
    assertTrue(libs.contains(maLibrairie2));
  }
}
