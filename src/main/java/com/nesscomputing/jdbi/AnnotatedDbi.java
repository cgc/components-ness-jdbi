/**
 * Copyright (C) 2012 Ness Computing, Inc.
 *
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
package com.nesscomputing.jdbi;

import org.skife.jdbi.v2.IDBI;
import org.skife.jdbi.v2.tweak.StatementLocator;


/**
 * Wraps the DBI so that all all Handles use an AnnotatedStatementLocator. This is used as follows:
 *
 * <pre>
 * &#64;StatementLocation("foo-sql.st");
 * public class FooDAO {
 *
 *   &#64;Inject
 *   public FooDAO(final IDBI dbi) {
 *    this.dbi = new AnnotatedDbi(dbi, this);
 *   }
 *
 *   ...
 * }
 * </pre>
 *
 * @see AnnotatedStatementLocator
 */
public class AnnotatedDbi extends DbiAdapter
{
    public AnnotatedDbi(final IDBI delegate, final Object annotatedClass)
    {
        super(new WrappingDbi.Builder(delegate).setLocator(new AnnotatedStatementLocator(annotatedClass)).build());
    }

    public AnnotatedDbi(final IDBI delegate, final Object annotatedClass, final StatementLocator defaultLocator)
    {
        super(new WrappingDbi.Builder(delegate).setLocator(new AnnotatedStatementLocator(annotatedClass, defaultLocator)).build());
    }
}

