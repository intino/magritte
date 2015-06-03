package siani.tara.dsls;

import siani.tara.semantic.model.Tag;
import siani.tara.semantic.model.Tara;

import java.util.Locale;

import static siani.tara.semantic.constraints.RuleFactory.*;

public class Monet extends Tara {
	public Monet() {
		in("").def(context().allow(multiple("Sequence"), multiple("Role"), multiple("Environment"), multiple("User"), multiple("Thesaurus"), multiple("Container"), multiple("Dialog"), multiple("Document"), multiple("Text"), multiple("Memo"), multiple("Number"), multiple("Check"), multiple("Date"), multiple("Picture"), multiple("Link"), multiple("Serial"), multiple("Select"), multiple("Section"), multiple("Container"), multiple("Dialog"), multiple("Document"), multiple("Text"), multiple("Memo"), multiple("Number"), multiple("Check"), multiple("Date"), multiple("Picture"), multiple("Link"), multiple("Serial"), multiple("Select"), multiple("Section"), multiple("Text"), multiple("Memo"), multiple("Number"), multiple("Check"), multiple("Date"), multiple("Picture"), multiple("Link"), multiple("Serial"), multiple("Select"), multiple("Section"), multiple("Index"), multiple("Multiple"), multiple("Target"), multiple("Collectable"), multiple("Prototypable"), multiple("Georeferenced"), multiple("Exportable"), multiple("Importable"), multiple("Restricted"), multiple("Process"), multiple("Service"), multiple("Job"), multiple("Process"), multiple("Service"), multiple("Job"), multiple("Mission"), multiple("Place"), multiple("Initial"), multiple("Terminal"), multiple("Junction"), multiple("Form"), multiple("Table"), multiple("Poll"), multiple("Form"), multiple("Table"), multiple("Poll"), multiple("Collection"), multiple("Folder"), multiple("FollowerList"), multiple("TaskList"), multiple("RevisionList"), multiple("ReadOnly"), multiple("Collection.Display"), multiple("FollowerList.Display"), name()));
		in("Sequence").def(context("Concept").allow(parameter("value", "integer", false, 0, "", "TERMINAL"), name()).assume(isTerminalInstance()));
		in("Role").def(context("Concept").allow(parameter("from", "date", false, 0, "", "TERMINAL"), parameter("to", "date", false, 1, "", "TERMINAL"), name()));
		in("Environment").def(context("Concept").allow(multiple("Container"), name()));
		in("User").def(context("Concept").allow(multiple("Role"), parameter("username", "string", false, 0, "", "TERMINAL"), parameter("name", "string", false, 1, "", "TERMINAL"), parameter("email", "string", false, 2, "", "TERMINAL"), parameter("photo", "file", false, 3, "[pngjpg]", "TERMINAL"), name()).require(_multiple("Environment"), _parameter("username", "string", false, 0, "", "TERMINAL"), _parameter("name", "string", false, 1, "", "TERMINAL"), _parameter("email", "string", false, 2, "", "TERMINAL"), _parameter("photo", "file", false, 3, "[pngjpg]", "TERMINAL")).assume(isTerminalInstance()));
		in("Thesaurus").def(context("Concept").allow(multiple("Thesaurus.Term", Tag.TERMINAL), name()).assume(isTerminalInstance()));
		in("Thesaurus.Term").def(context("Concept").allow(multiple("Thesaurus.Term", Tag.TERMINAL), parameter("key", "string", false, 0, "", "TERMINAL"), parameter("label", "string", false, 1, "", "TERMINAL"), name()).assume(isTerminal()));
		in("Entity.Toolbar").def(context("Concept").allow(multiple("Entity.Toolbar.Operation"), name()).assume(isFeatureInstance()));
		in("Entity.Toolbar.Operation").def(context("Concept").allow(parameter("dialog", new String[]{"Dialog"}, false, 1, ""), parameter("refresh", "native", false, 3, "OperationRefresh#"), name()).require(_parameter("label", "string", false, 0, ""), _parameter("execute", "native", false, 2, "Operation#")));
		in("Container").def(context("Concept").allow(multiple("Entity.Toolbar"), multiple("Text", Tag.SINGLE), multiple("Memo", Tag.SINGLE), multiple("Number", Tag.SINGLE), multiple("Check", Tag.SINGLE), multiple("Date", Tag.SINGLE), multiple("Picture", Tag.SINGLE), multiple("Link", Tag.SINGLE), multiple("Serial", Tag.SINGLE), multiple("Select", Tag.SINGLE), multiple("Section", Tag.SINGLE), multiple("Multiple"), multiple("Dialog", Tag.SINGLE), multiple("Collectable"), multiple("Document"), parameter("onCreate", "native", false, 0, "OnCreate#"), parameter("onOpen", "native", false, 1, "OnOpen#"), parameter("onClose", "native", false, 2, "OnClose#"), parameter("onSave", "native", false, 3, "OnSave#"), parameter("onChange", "native", false, 4, "OnChange#"), parameter("onRemove", "native", false, 5, "OnRemove#"), name(), facet("Prototypable").require(_parameter("entities", new String[]{"Container", "Dialog", "Document", "Field"}, false, 0, "")), facet("Collectable"), facet("Exportable").require(_parameter("onExport", "native", false, 0, "OnExport")), facet("Target"), facet("Restricted"), facet("Service").require(_parameter("comments", "string", false, 1, ""), _parameter("state:word", new String[]{"Created", "Waiting", "Pending", "Finished", "Aborted"}, false, 2, "")).allow(parameter("urgent", "boolean", false, 0, "")), facet("Process").require(_parameter("comments", "string", false, 1, ""), _parameter("state:word", new String[]{"Created", "Waiting", "Pending", "Finished", "Aborted"}, false, 2, "")).allow(parameter("urgent", "boolean", false, 0, "")), facet("Importable").require(_parameter("onImport", "native", false, 0, "OnImport")), facet("Job").require(_parameter("comments", "string", false, 1, ""), _parameter("state:word", new String[]{"Created", "Waiting", "Pending", "Finished", "Aborted"}, false, 2, "")).allow(parameter("urgent", "boolean", false, 0, ""))).require(_multiple("Container.View")));
		in("Entity.Toolbar").def(context("Concept").allow(multiple("Entity.Toolbar.Operation"), name()).assume(isFeatureInstance()));
		in("Container.View").def(context("Concept").allow(multiple("Container.View.Tab"), single("Container.View.HideBreadCrumbs"), parameter("refresh", "native", false, 0, "Refresh#"), name()).assume(isFeatureInstance()));
		in("Container.View.Tab").def(context("Concept").allow(parameter("refresh", "native", false, 1, "TabRefresh#"), name(), facet("Form"), facet("TaskList"), facet("RevisionList"), facet("Poll"), facet("Collection").require(_parameter("index", new String[]{"Index"}, false, 1, ""), _parameter("collectables", new String[]{"Collectable"}, false, 2, "")).allow(parameter("mode:word", new String[]{"table", "list"}, false, 0, ""), parameter("filter", "native", false, 3, "Filter")), facet("Folder").require(_parameter("douments", new String[]{"Document"}, false, 0, ""))).require(_parameter("label", "string", false, 0, "")));
		in("Container.View.HideBreadCrumbs").def(context("Concept").allow(name()));
		in("Dialog").def(context("Concept").allow(multiple("Entity.Toolbar"), multiple("Text"), multiple("Memo"), multiple("Number"), multiple("Check"), multiple("Date"), multiple("Picture"), multiple("Link"), multiple("Serial"), multiple("Select"), multiple("Section"), multiple("Multiple"), parameter("onCreate", "native", false, 0, "OnCreate#"), parameter("onOpen", "native", false, 1, "OnOpen#"), parameter("onClose", "native", false, 2, "OnClose#"), parameter("onSave", "native", false, 3, "OnSave#"), parameter("onChange", "native", false, 4, "OnChange#"), parameter("onRemove", "native", false, 5, "OnRemove#"), name(), facet("Restricted")).require(_multiple("Dialog.View"), _parameter("label", "string", false, 6, "")));
		in("Entity.Toolbar").def(context("Concept").allow(multiple("Entity.Toolbar.Operation"), name()).assume(isFeatureInstance()));
		in("Dialog.View").def(context("Concept").allow(parameter("refresh", "native", false, 0, "Refresh#"), name(), facet("Form"), facet("Poll")).assume(isFeatureInstance()));
		in("Document").def(context("Concept").allow(multiple("Entity.Toolbar"), multiple("Document.Template"), multiple("Document.SignatureFolder"), parameter("onCreate", "native", false, 0, "OnCreate#"), parameter("onOpen", "native", false, 1, "OnOpen#"), parameter("onClose", "native", false, 2, "OnClose#"), parameter("onSave", "native", false, 3, "OnSave#"), parameter("onChange", "native", false, 4, "OnChange#"), parameter("onRemove", "native", false, 5, "OnRemove#"), parameter("value", "file", false, 6, "", "TERMINAL"), parameter("signatures", "string", true, 7, "", "TERMINAL"), name(), facet("Restricted")));
		in("Entity.Toolbar").def(context("Concept").allow(multiple("Entity.Toolbar.Operation"), name()).assume(isFeatureInstance()));
		in("Document.Template").def(context("Concept").allow(name()).require(_parameter("template", "file", false, 0, "[doxodt]")).assume(isFeatureInstance()));
		in("Document.SignatureFolder").def(context("Concept").allow(parameter("position:word", new String[]{"TopOfDocument", "BottomOfDocument"}, false, 0, ""), parameter("onTerminate", "native", false, 1, "OnTerminate#"), name()).require(_multiple("Document.SignatureFolder.Signature")).assume(isFeatureInstance()));
		in("Document.SignatureFolder.Signature").def(context("Concept").allow(multiple("Document.SignatureFolder.Signature.After"), parameter("repeat", "integer", false, 2, "times"), name(), facet("Multiple").require(_parameter("times", "integer", false, 0, "")), facet("Restricted").require(_parameter("roles", new String[]{"Role"}, false, 0, ""))).require(_parameter("label", "string", false, 0, ""), _parameter("onSign", "native", false, 1, "OnSign#")));
		in("Document.SignatureFolder.Signature.After").def(context("Concept").allow(name()).require(_parameter("signature", new String[]{"Document.SignatureFolder.Signature"}, false, 0, "")));
		in("Entity.Toolbar").def(context("Concept").allow(multiple("Entity.Toolbar.Operation"), name()).assume(isFeatureInstance()));
		in("Text").def(context("Concept").allow(multiple("Entity.Toolbar"), multiple("Text.Pattern"), multiple("Text.Length"), multiple("Text.Format"), parameter("onCreate", "native", false, 0, "OnCreate#"), parameter("onOpen", "native", false, 1, "OnOpen#"), parameter("onClose", "native", false, 2, "OnClose#"), parameter("onSave", "native", false, 3, "OnSave#"), parameter("onChange", "native", false, 4, "OnChange#"), parameter("onRemove", "native", false, 5, "OnRemove#"), parameter("value", "string", false, 7, "", "TERMINAL"), name(), facet("Multiple")).require(_parameter("validate", "native", false, 6, "FieldValidate#")));
		in("Entity.Toolbar").def(context("Concept").allow(multiple("Entity.Toolbar.Operation"), name()).assume(isFeatureInstance()));
		in("Text.Pattern").def(context("Concept").allow(name()).require(_parameter("mode:word", new String[]{"Url", "Email", "Password"}, false, 0, "")).assume(isFeatureInstance()));
		in("Text.Length").def(context("Concept").allow(parameter("min", "natural", false, 0, ""), name()).require(_parameter("max", "natural", false, 1, "")).assume(isFeatureInstance()));
		in("Text.Format").def(context("Concept").allow(name()).require(_parameter("mode:word", new String[]{"Uppercase", "Lowercase", "Sentence", "Title"}, false, 0, "")).assume(isFeatureInstance()));
		in("Memo").def(context("Concept").allow(multiple("Entity.Toolbar"), multiple("Memo.Length"), multiple("Memo.Format"), parameter("onCreate", "native", false, 0, "OnCreate#"), parameter("onOpen", "native", false, 1, "OnOpen#"), parameter("onClose", "native", false, 2, "OnClose#"), parameter("onSave", "native", false, 3, "OnSave#"), parameter("onChange", "native", false, 4, "OnChange#"), parameter("onRemove", "native", false, 5, "OnRemove#"), parameter("value", "string", false, 7, "", "TERMINAL"), name(), facet("Multiple")).require(_parameter("validate", "native", false, 6, "FieldValidate#")));
		in("Entity.Toolbar").def(context("Concept").allow(multiple("Entity.Toolbar.Operation"), name()).assume(isFeatureInstance()));
		in("Memo.Length").def(context("Concept").allow(parameter("min", "natural", false, 0, ""), name()).require(_parameter("max", "natural", false, 1, "")).assume(isFeatureInstance()));
		in("Memo.Format").def(context("Concept").allow(parameter("mode:word", new String[]{"Plain", "RichText"}, false, 0, ""), name()).assume(isFeatureInstance()));
		in("Number").def(context("Concept").allow(multiple("Entity.Toolbar"), multiple("Number.Metric"), multiple("Number.Range"), parameter("onCreate", "native", false, 0, "OnCreate#"), parameter("onOpen", "native", false, 1, "OnOpen#"), parameter("onClose", "native", false, 2, "OnClose#"), parameter("onSave", "native", false, 3, "OnSave#"), parameter("onChange", "native", false, 4, "OnChange#"), parameter("onRemove", "native", false, 5, "OnRemove#"), parameter("value", "double", false, 7, "", "TERMINAL"), name(), facet("Multiple")).require(_parameter("validate", "native", false, 6, "FieldValidate#")));
		in("Entity.Toolbar").def(context("Concept").allow(multiple("Entity.Toolbar.Operation"), name()).assume(isFeatureInstance()));
		in("Number.Metric").def(context("Concept").allow(name()).require(_parameter("label", "string", false, 0, "")).assume(isFeatureInstance()));
		in("Number.Range").def(context("Concept").allow(name()).require(_parameter("min", "integer", false, 0, ""), _parameter("max", "integer", false, 1, "")).assume(isFeatureInstance()));
		in("Check").def(context("Concept").allow(multiple("Entity.Toolbar"), parameter("onCreate", "native", false, 0, "OnCreate#"), parameter("onOpen", "native", false, 1, "OnOpen#"), parameter("onClose", "native", false, 2, "OnClose#"), parameter("onSave", "native", false, 3, "OnSave#"), parameter("onChange", "native", false, 4, "OnChange#"), parameter("onRemove", "native", false, 5, "OnRemove#"), parameter("value", "boolean", false, 7, "", "TERMINAL"), name(), facet("Multiple")).require(_parameter("validate", "native", false, 6, "FieldValidate#")));
		in("Entity.Toolbar").def(context("Concept").allow(multiple("Entity.Toolbar.Operation"), name()).assume(isFeatureInstance()));
		in("Date").def(context("Concept").allow(multiple("Entity.Toolbar"), parameter("onCreate", "native", false, 0, "OnCreate#"), parameter("onOpen", "native", false, 1, "OnOpen#"), parameter("onClose", "native", false, 2, "OnClose#"), parameter("onSave", "native", false, 3, "OnSave#"), parameter("onChange", "native", false, 4, "OnChange#"), parameter("onRemove", "native", false, 5, "OnRemove#"), parameter("value", "date", false, 7, "", "TERMINAL"), name(), facet("Multiple")).require(_multiple("Date.Precision"), _multiple("Date.Scope"), _parameter("validate", "native", false, 6, "FieldValidate#")));
		in("Entity.Toolbar").def(context("Concept").allow(multiple("Entity.Toolbar.Operation"), name()).assume(isFeatureInstance()));
		in("Date.Precision").def(context("Concept").allow(name()).require(_parameter("value:word", new String[]{"Years", "Months", "Days", "Hours", "Minutes", "Seconds"}, false, 0, "")).assume(isFeatureInstance()));
		in("Date.Scope").def(context("Concept").allow(multiple("Date.Scope.Near"), name()).require(_parameter("value:word", new String[]{"Future", "Past"}, false, 0, "")).assume(isFeatureInstance()));
		in("Date.Scope.Near").def(context("Concept").allow(name()));
		in("Picture").def(context("Concept").allow(multiple("Entity.Toolbar"), parameter("onCreate", "native", false, 0, "OnCreate#"), parameter("onOpen", "native", false, 1, "OnOpen#"), parameter("onClose", "native", false, 2, "OnClose#"), parameter("onSave", "native", false, 3, "OnSave#"), parameter("onChange", "native", false, 4, "OnChange#"), parameter("onRemove", "native", false, 5, "OnRemove#"), parameter("value", "file", false, 7, "[jpgpng]", "TERMINAL"), name(), facet("Multiple")).require(_parameter("validate", "native", false, 6, "FieldValidate#")));
		in("Entity.Toolbar").def(context("Concept").allow(multiple("Entity.Toolbar.Operation"), name()).assume(isFeatureInstance()));
		in("Link").def(context("Concept").allow(multiple("Entity.Toolbar"), parameter("onCreate", "native", false, 0, "OnCreate#"), parameter("onOpen", "native", false, 1, "OnOpen#"), parameter("onClose", "native", false, 2, "OnClose#"), parameter("onSave", "native", false, 3, "OnSave#"), parameter("onChange", "native", false, 4, "OnChange#"), parameter("onRemove", "native", false, 5, "OnRemove#"), parameter("target", new String[]{"Target"}, false, 7, "", "TERMINAL"), name(), facet("Multiple")).require(_multiple("Link.View"), _parameter("validate", "native", false, 6, "FieldValidate#"), redefine("target", "Target")));
		in("Entity.Toolbar").def(context("Concept").allow(multiple("Entity.Toolbar.Operation"), name()).assume(isFeatureInstance()));
		in("Link.View").def(context("Concept").allow(name(), facet("Collection").require(_parameter("index", new String[]{"Index"}, false, 1, ""), _parameter("collectables", new String[]{"Collectable"}, false, 2, "")).allow(parameter("mode:word", new String[]{"table", "list"}, false, 0, ""), parameter("filter", "native", false, 3, "Filter"))).assume(isFeatureInstance()));
		in("Serial").def(context("Concept").allow(multiple("Entity.Toolbar"), parameter("onCreate", "native", false, 0, "OnCreate#"), parameter("onOpen", "native", false, 1, "OnOpen#"), parameter("onClose", "native", false, 2, "OnClose#"), parameter("onSave", "native", false, 3, "OnSave#"), parameter("onChange", "native", false, 4, "OnChange#"), parameter("onRemove", "native", false, 5, "OnRemove#"), parameter("value", "string", false, 8, "", "TERMINAL"), name(), facet("Multiple")).require(_multiple("Serial.Format"), _parameter("validate", "native", false, 6, "FieldValidate#"), _parameter("sequence", new String[]{"Sequence"}, false, 7, "")));
		in("Entity.Toolbar").def(context("Concept").allow(multiple("Entity.Toolbar.Operation"), name()).assume(isFeatureInstance()));
		in("Serial.Format").def(context("Concept").allow(name()).require(_parameter("value", "string", false, 0, "")).assume(isFeatureInstance()));
		in("Select").def(context("Concept").allow(multiple("Entity.Toolbar"), single("Thesaurus"), parameter("onCreate", "native", false, 0, "OnCreate#"), parameter("onOpen", "native", false, 1, "OnOpen#"), parameter("onClose", "native", false, 2, "OnClose#"), parameter("onSave", "native", false, 3, "OnSave#"), parameter("onChange", "native", false, 4, "OnChange#"), parameter("onRemove", "native", false, 5, "OnRemove#"), parameter("value", new String[]{"Thesaurus.Term"}, false, 7, "", "TERMINAL"), name(), facet("Multiple")).require(_multiple("Select.Source"), _parameter("validate", "native", false, 6, "FieldValidate#"), redefine("value", "Thesaurus.Term")));
		in("Entity.Toolbar").def(context("Concept").allow(multiple("Entity.Toolbar.Operation"), name()).assume(isFeatureInstance()));
		in("Select.Source").def(context("Concept").allow(name()).require(_parameter("thesaurus", new String[]{"Thesaurus"}, false, 0, "")).assume(isFeatureInstance()));
		in("Section").def(context("Concept").allow(multiple("Entity.Toolbar"), multiple("Text", Tag.SINGLE), multiple("Memo", Tag.SINGLE), multiple("Number", Tag.SINGLE), multiple("Check", Tag.SINGLE), multiple("Date", Tag.SINGLE), multiple("Picture", Tag.SINGLE), multiple("Link", Tag.SINGLE), multiple("Serial", Tag.SINGLE), multiple("Select", Tag.SINGLE), multiple("Section", Tag.SINGLE), multiple("Multiple"), parameter("onCreate", "native", false, 0, "OnCreate#"), parameter("onOpen", "native", false, 1, "OnOpen#"), parameter("onClose", "native", false, 2, "OnClose#"), parameter("onSave", "native", false, 3, "OnSave#"), parameter("onChange", "native", false, 4, "OnChange#"), parameter("onRemove", "native", false, 5, "OnRemove#"), name(), facet("Multiple")).require(_multiple("Section.View"), _parameter("validate", "native", false, 6, "FieldValidate#")));
		in("Entity.Toolbar").def(context("Concept").allow(multiple("Entity.Toolbar.Operation"), name()).assume(isFeatureInstance()));
		in("Section.View").def(context("Concept").allow(name(), facet("Table"), facet("Form"), facet("Poll")).assume(isFeatureInstance()));
		in("Index").def(context("Concept").allow(multiple("Index.Entry", Tag.TERMINAL), name()).assume(isTerminalInstance()));
		in("Index.Entry").def(context("Concept").allow(name()).assume(isTerminal()));
		in("Multiple").def(context("Concept").allow(name()).assume(isFacetInstance()));
		in("Target").def(context("Concept").allow(multiple("Target.Publish"), name()).assume(isFacetInstance()));
		in("Target.Publish").def(context("Concept").allow(name()).require(_parameter("fields", new String[]{"Text", "Memo", "Number", "Check", "Date", "Picture", "Link", "Serial", "Select", "Section"}, true, 0, "")).assume(isFeatureInstance()));
		in("Collectable").def(context("Concept").allow(multiple("Collectable.Publish"), name(), facet("Georeferenced").require(_parameter("wktPlace", "string", false, 0, "")), facet("FollowerList")).assume(isFacetInstance()));
		in("Collectable.Publish").def(context("Concept").allow(name()).require(_parameter("fields", new String[]{"Text", "Memo", "Number", "Check", "Date", "Picture", "Link", "Serial", "Select", "Section"}, true, 0, "")).assume(isFeatureInstance()));
		in("Prototypable").def(context("Concept").allow(name()).require(_parameter("entities", new String[]{"Container", "Dialog", "Document", "Field"}, true, 0, "")).assume(isFacetInstance()));
		in("Georeferenced").def(context("Concept").allow(name()).require(_parameter("wktPlace", "string", false, 0, "")).assume(isFacetInstance()));
		in("Exportable").def(context("Concept").allow(name()).require(_parameter("onExport", "native", false, 0, "OnExport#")).assume(isFacetInstance()));
		in("Importable").def(context("Concept").allow(name()).require(_parameter("onImport", "native", false, 0, "OnImport#")).assume(isFacetInstance()));
		in("Restricted").def(context("Concept").allow(name()).require(_parameter("roles", new String[]{"Role"}, true, 0, "")).assume(isFacetInstance()));
		in("Task.Auditory").def(context("Concept").allow(parameter("createDate", "date", false, 0, "", "TERMINAL"), parameter("startDate", "date", false, 1, "", "TERMINAL"), parameter("endDate", "date", false, 2, "", "TERMINAL"), name()).require(_parameter("createDate", "date", false, 0, "", "TERMINAL"), _parameter("startDate", "date", false, 1, "", "TERMINAL"), _parameter("endDate", "date", false, 2, "", "TERMINAL")).assume(isTerminalInstance()));
		in("Task.Suggestion").def(context("Concept").allow(parameter("startDate", "date", false, 0, "", "TERMINAL"), parameter("endDate", "date", false, 1, "", "TERMINAL"), name()).require(_parameter("startDate", "date", false, 0, "", "TERMINAL"), _parameter("endDate", "date", false, 1, "", "TERMINAL")).assume(isTerminalInstance()));
		in("Process").def(context("Concept").allow(multiple("Task.Auditory"), multiple("Task.Suggestion"), parameter("urgent", "boolean", false, 0, ""), name()).require(_parameter("comments", "string", false, 1, ""), _parameter("state:word", new String[]{"Created", "Waiting", "Pending", "Finished", "Aborted"}, false, 2, "")).assume(isFacetInstance()));
		in("Task.Auditory").def(context("Concept").allow(name()).assume(isTerminalInstance()));
		in("Task.Suggestion").def(context("Concept").allow(name()).assume(isTerminalInstance()));
		in("Service").def(context("Concept").allow(multiple("Task.Auditory"), multiple("Task.Suggestion"), parameter("urgent", "boolean", false, 0, ""), name()).require(_parameter("comments", "string", false, 1, ""), _parameter("state:word", new String[]{"Created", "Waiting", "Pending", "Finished", "Aborted"}, false, 2, "")).assume(isFacetInstance()));
		in("Task.Auditory").def(context("Concept").allow(name()).assume(isTerminalInstance()));
		in("Task.Suggestion").def(context("Concept").allow(name()).assume(isTerminalInstance()));
		in("Job").def(context("Concept").allow(multiple("Task.Auditory"), multiple("Task.Suggestion"), parameter("urgent", "boolean", false, 0, ""), name()).require(_parameter("comments", "string", false, 1, ""), _parameter("state:word", new String[]{"Created", "Waiting", "Pending", "Finished", "Aborted"}, false, 2, "")).assume(isFacetInstance()));
		in("Task.Auditory").def(context("Concept").allow(name()).assume(isTerminalInstance()));
		in("Task.Suggestion").def(context("Concept").allow(name()).assume(isTerminalInstance()));
		in("Mission").def(context("Concept").allow(multiple("Mission.Duty", Tag.TERMINAL), parameter("place", new String[]{"Place"}, false, 0, "", "TERMINAL"), name()).require(_parameter("place", new String[]{"Place"}, false, 0, "", "TERMINAL")).assume(isTerminalInstance()));
		in("Mission.Duty").def(context("Concept").allow(parameter("value", "native", false, 0, "Duty#", "TERMINAL"), name()).assume(isTerminal()));
		in("Place").def(context("Concept").allow(parameter("label", "string", false, 0, "", "TERMINAL"), name(), facet("Junction"), facet("Initial"), facet("Terminal").allow(parameter("mode:word", new String[]{"Terminate", "Abort"}, false, 0, ""))).require(_parameter("label", "string", false, 0, "", "TERMINAL")).assume(isTerminalInstance()));
		in("Initial").def(context("Concept").allow(name()).assume(isFacetInstance()));
		in("Terminal").def(context("Concept").allow(parameter("mode:word", new String[]{"Terminate", "Abort"}, false, 0, ""), name()).assume(isFacetInstance()));
		in("Junction").def(context("Concept").allow(multiple("Junction.Exit"), name()).assume(isFacetInstance()));
		in("Junction.Exit").def(context("Concept").allow(multiple("Junction.Exit.OnTimeout"), multiple("Junction.Exit.OnResponse"), multiple("Junction.Exit.OnTimeout"), multiple("Junction.Exit.OnResponse"), multiple("Junction.Exit.LogStep"), multiple("Junction.Exit.Delegate"), multiple("Junction.Exit.LogStep"), multiple("Junction.Exit.Delegate"), name()).require(_parameter("place", new String[]{"Place"}, false, 0, "")));
		in("Junction.Exit.OnTimeout").def(context("Concept").allow(name()).require(_parameter("value", "measure", false, 0, "Duration")));
		in("Junction.Exit.OnResponse").def(context("Concept").allow(name()));
		in("Junction.Exit.LogStep").def(context("Concept").allow(name()));
		in("Junction.Exit.Delegate").def(context("Concept").allow(name()));
		in("FieldView.Display").def(context("Concept").allow(parameter("mode:word", new String[]{"Reduced", "Extended"}, false, 0, ""), name()).require(_parameter("fields", new String[]{"Text", "Memo", "Number", "Check", "Date", "Picture", "Link", "Serial", "Select", "Section"}, true, 1, ""), _parameter("layout", "string", false, 2, "")));
		in("FieldView.Disable").def(context("Concept").allow(name()).require(_parameter("fields", new String[]{"Text", "Memo", "Number", "Check", "Date", "Picture", "Link", "Serial", "Select", "Section"}, true, 0, "")));
		in("Form").def(context("Concept").allow(multiple("FieldView.Display"), multiple("FieldView.Disable"), name(), facet("ReadOnly")).assume(isFacetInstance()));
		in("FieldView.Display").def(context("Concept").allow(name()));
		in("FieldView.Disable").def(context("Concept").allow(name()));
		in("Table").def(context("Concept").allow(multiple("FieldView.Display"), multiple("FieldView.Disable"), name(), facet("ReadOnly")).assume(isFacetInstance()));
		in("FieldView.Display").def(context("Concept").allow(name()));
		in("FieldView.Disable").def(context("Concept").allow(name()));
		in("Poll").def(context("Concept").allow(multiple("FieldView.Display"), multiple("FieldView.Disable"), name(), facet("ReadOnly")).assume(isFacetInstance()));
		in("FieldView.Display").def(context("Concept").allow(name()));
		in("FieldView.Disable").def(context("Concept").allow(name()));
		in("Collection").def(context("Concept").allow(multiple("Collection.Display"), parameter("mode:word", new String[]{"table", "list"}, false, 0, ""), parameter("index", new String[]{"Index"}, false, 1, "", "TERMINAL"), parameter("filter", "native", false, 3, "Filter#"), name(), facet("ReadOnly")).require(_parameter("collectables", new String[]{"Collectable"}, true, 2, "")).assume(isFacetInstance()));
		in("Collection.Display").def(context("Concept").allow(multiple("ReferenceDisplay.Title"), multiple("ReferenceDisplay.Picture"), multiple("ReferenceDisplay.Line"), multiple("ReferenceDisplay.Highlight"), multiple("ReferenceDisplay.Footer"), name()));
		in("ReferenceDisplay.Title").def(context("Concept").allow(name()));
		in("ReferenceDisplay.Picture").def(context("Concept").allow(name()));
		in("ReferenceDisplay.Line").def(context("Concept").allow(name()));
		in("ReferenceDisplay.Highlight").def(context("Concept").allow(name()));
		in("ReferenceDisplay.Footer").def(context("Concept").allow(name()));
		in("Folder").def(context("Concept").allow(name()).require(_parameter("douments", new String[]{"Document"}, true, 0, "")).assume(isFacetInstance()));
		in("FollowerList").def(context("Concept").allow(multiple("FollowerList.Display"), name()).assume(isFacetInstance()));
		in("FollowerList.Display").def(context("Concept").allow(multiple("ReferenceDisplay.Title"), multiple("ReferenceDisplay.Picture"), multiple("ReferenceDisplay.Line"), multiple("ReferenceDisplay.Highlight"), multiple("ReferenceDisplay.Footer"), name()));
		in("ReferenceDisplay.Title").def(context("Concept").allow(name()));
		in("ReferenceDisplay.Picture").def(context("Concept").allow(name()));
		in("ReferenceDisplay.Line").def(context("Concept").allow(name()));
		in("ReferenceDisplay.Highlight").def(context("Concept").allow(name()));
		in("ReferenceDisplay.Footer").def(context("Concept").allow(name()));
		in("TaskList").def(context("Concept").allow(name()).assume(isFacetInstance()));
		in("RevisionList").def(context("Concept").allow(name()).assume(isFacetInstance()));
		in("ReadOnly").def(context("Concept").allow(name()).assume(isFacetInstance()));
		in("ReferenceDisplay.Title").def(context("Concept").allow(name()).require(_parameter("attribute", "string", false, 0, "")));
		in("ReferenceDisplay.Picture").def(context("Concept").allow(name()).require(_parameter("attribute", "string", false, 0, "")));
		in("ReferenceDisplay.Line").def(context("Concept").allow(name()).require(_parameter("attribute", "string", true, 0, "")));
		in("ReferenceDisplay.Highlight").def(context("Concept").allow(name()).require(_parameter("attributes", "string", true, 0, "")));
		in("ReferenceDisplay.Footer").def(context("Concept").allow(name()).require(_parameter("attributes", "string", true, 0, "")));
	}

	@Override
	public String languageName() {
		return "Monet";
	}

	@Override
	public Locale locale() {
		return Locale.ENGLISH;
	}

	@Override
	public boolean isTerminalLanguage() {
		return false;
	}
}
