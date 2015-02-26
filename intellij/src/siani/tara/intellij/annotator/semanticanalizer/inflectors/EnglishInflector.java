/*
 * Copyright 2014 SIANI - ULPGC
 * Octavio Roncal Andrés
 * José Juan Hernández Cabrera
 * José Évora Gomez
 *
 * This File is Part of itrules Project
 *
 * itrules Project is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * itrules Project is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with itrules Library.  If not, see <http://www.gnu.org/licenses/>.
 */

package siani.tara.intellij.annotator.semanticanalizer.inflectors;

import org.siani.itrules.formatter.Inflector;

public class EnglishInflector extends Inflector {

    @Override
    public String plural(String word) {
        if (isIrregular(word)) return irregularPlural(word);
        return doReplaces(word + "s");
    }

    @Override
    protected void setReplaces() {
        addReplace("sss", "sses");
        addReplace("ss", "ses");
        addReplace("shs", "shes");
        addReplace("chs", "ches");
        addReplace("xs", "xes");
        addReplace("zzs", "zzes");
        addReplace("zs", "zzes");
        addReplace("fes", "ves");
        addReplace("fs", "ves");
        addReplace("siss", "ses");
        setConsonantYReplaces();
    }

    private void setConsonantYReplaces() {
        for (char consonant : consonants())
            addReplace(consonant + "ys", consonant + "ies");
    }

    @Override
    protected void setIrregulars() {
        addIrregular("man", "men");
        addIrregular("woman", "women");
        addIrregular("child", "children");
        addIrregular("foot", "feet");
        addIrregular("tooth", "teeth");
        addIrregular("goose", "geese");
        addIrregular("mouse", "mice");
        addIrregular("sheep", "sheep");
        addIrregular("deer", "deer");
        addIrregular("moose", "moose");
        addIrregular("aircraft", "aircraft");
        addIrregular("potato", "potatoes");
        addIrregular("tomato", "tomatoes");
        addIrregular("echo", "echoes");
        addIrregular("hero", "heroes");
        addIrregular("torpedo", "torpedoes");
        addIrregular("buffalo", "buffaloes");
    }

}
