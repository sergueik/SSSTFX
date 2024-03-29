﻿### Info

The __SSSTFX__ project converts Selenium TNG __Side__ JSON recordings to __Straight__ __Selenium__  code in Java or other languages via __jTwig__ with __javaFX__.
Also offers the UI for minimal editing of the test workflow before generaring the code, similar to [SWET/FX](https://github.com/sergueik/selenium_java/tree/master/swet_javafx) or [SWET/SWT](https://github.com/sergueik/SWET).
(likely will choose JavaFX rendering).

![edit step details](https://github.com/sergueik/SSSTFX/blob/master/screenshots/edit.png)

#### Action
First deserialize the `*.side` JSON
using basic [gson](https://google.github.io/gson/apidocs/com/google/gson/Gson.html) and reflection.

```json
{
  "id": "837d3acd-285e-478a-8d46-817df0a5b4d9",
  "name": "Google<br>",
  "url": "https://www.google.com \t",
  "tests": [
    {
      "id": "ae13d6ad-c3f2-4fb8-aaeb-14af40f2b3b9",
      "name": "Google",
      "commands": [
        {
          "id": "160c2276-d9b3-4523-bdf3-b914111ca407",
          "comment": "",
          "command": "open",
          "target": "/images",
          "value": ""
        },
        {
          "id": "856ac533-41f0-4091-813d-6f865cf72985",
          "comment": "",
          "command": "open",
          "target": "/",
          "value": ""
        }
      ]
    }
  ],
  "suites": [
    {
      "id": "05e89807-cb33-4ca6-8ca4-10e1cdf127c3",
      "name": "Default Suite",
      "testNames": [
        "ae13d6ad-c3f2-4fb8-aaeb-14af40f2b3b9"
      ]
    }
  ],
  "urls": [
    "https://www.google.co.in",
    "https://www.google.co.in"
  ]
}

```

```java
SideRecording sideRecording = new Gson().fromJson(SeleniumIDEPayload,
  SideRecording.class);
```
into into a collecton of plain DTO class instances of the kind
```java
class SideRecording {
	private String id;
	private String name;
	private String url;
	private List<SideSuite> suites;
	private List<SideTest> tests;
	private List<String> urls;

  // ...
}

```
```java
class SideSuite {

	private String name;
	private String id;
	private List<String> tests;

  // ...
}
```

```java
class SideTest {

	private String name;
	private String id;
	private List<SideCommand> commands;

  // ...
}
```

```java
class SideCommand {

	private String name;
	private String id;
	private String command;
	private String comment;
	private String target;
	private String value;
  // ...
}
```

Second decrypts the strongly typed SideCommand instances derived from
```json
{
  "command": "highlight",
  "target": "id=lst-ib",
  "value": ""
},
{
  "command": "pause",
  "target": "",
  "value": "200"
},
{
  "command": "click",
  "target": "//input[@type='text']",
  "value": ""
},
{
  "command": "store",
  "target": "selenium ide",
  "value": "text"
},
{
  "command": "type",
  "target": "id=lst-ib",
  "value": "${text}"
},
{
  "command": "verifyElementPresent",
  "target": "//input[@value='Google Search']",
  "value": ""
},
{
  "command": "verifyText",
  "target": "id=SIvCob",
  "value": "Google offered in:"
},
{
  "command": "sendKeys",
  "target": "id=lst-ib",
  "value": "${KEY_ENTER}"
},

```
into `LocatorKind`, `LocatorArgument`, `Operation`, `OperationArgument` etc. properties o a `RealSideCommand` class:
```java
class RealSideCommand {

	private String id;
	private String name;
	private String command;
	private String comment;
	private String target;
	private Selector selector;
	private String selectorValue;
	private Operation operation;
	private String value; // the "value" is in fact the operation argument
  // ...
}
```
where
```java
public enum Selector {
  css("css"),
  id("id"),
  tagName("tag"),
  text("text");
  xpath("xpath"),
```
```java
public enum Operation {
  click("click"),
  clickAt("clickAt"),
  echo("echo"),
  highlight("highlight"),
  open("open"),
  pause("pause"),
  refresh("refresh"),
  sendKeys("sendKeys"),
  store("store"),
  storeTitle("storeTitle"),
  type("type"),
  verifyElementPresent("verifyElementPresent"),
  verifyText("verifyText"),
  verifyTitle("verifyTitle"),
  windowMaximize("windowMaximize"),
// ...


```
like e.g. in [SKDF](https://github.com/sergueik/SKDF) or other keyword driven frameworks do -
work in progress but seems easy since the input stream of ojects is strongly typed. The json fragmenr example above
could be clearly mapped via regexp extraction and String to class/method, dispatch dictionary  like

```java
static {
  methodTable.put("CLEAR_TEXT", "clearText");
  methodTable.put("CLICK", "clickButton");
  methodTable.put("CLICK_BUTTON", "clickButton");
  methodTable.put("CLICK_CHECKBOX", "clickCheckBox");
  methodTable.put("CLICK_LINK", "clickLink");
  methodTable.put("CLICK_RADIO", "clickRadioButton");
  methodTable.put("CLOSE_BROWSER", "closeBrowser");
  methodTable.put("CONFIRM_ALERT", "confirmAlert");
  methodTable.put("COUNT_ELEMENTS", "countElements");
  methodTable.put("CREATE_BROWSER", "openBrowser");
  methodTable.put("DISMISS_ALERT", "dismissAlert");
  methodTable.put("ELEMENT_PRESENT", "elementPresent");
  methodTable.put("SEND_KEYS_ALERT", "sendKeysAlert");
  methodTable.put("GET_ATTR", "getElementAttribute");
  methodTable.put("GET_TEXT", "getElementText");
  methodTable.put("GOTO_URL", "navigateTo");
  methodTable.put("SELECT_OPTION", "selectDropDown");
  methodTable.put("SEND_KEYS", "enterText");
  methodTable.put("SET_TEXT", "enterText");
  methodTable.put("SWITCH_FRAME", "switchFrame");
  methodTable.put("VERIFY_ATTR", "verifyAttribute");
  methodTable.put("VERIFY_TAG", "verifyTag");
  methodTable.put("VERIFY_TEXT", "verifyText");
  methodTable.put("WAIT", "wait");
  methodTable.put("WAIT_ELEMENT", "waitVisible");
  methodTable.put("WAIT_ELEMENT_CLICAKBLE", "waitClickable");
  methodTable.put("WAIT_URL_CHANGE", "waitURLChange");
}
```
- the fragment is borrowed from [SKDF](https://github.com/sergueik/SKDF) verbatim - the methods and their tags will be different.

![review workflow](https://github.com/sergueik/SSSTFX/blob/master/screenshots/review.png)

Finally utilizes collection of [jtwig](http://jtwig.org/documentation/reference) templates like example below
borrowed from [SWET](https://github.com/sergueik/SWET)
```java
{#
template: Core Selenium Java
#}

// {{ ElementCodeName }}
{% if (ElementText != '') -%}
// {{ ElementText }}
{% endif -%}
WebElement element = driver.findElement(
{% if (ElementSelectedBy == 'ElementCssSelector') -%}
  By.cssSelector("{{ ElementCssSelector }}")
{% elseif (ElementSelectedBy == 'ElementXPath') -%}
  By.xpath("{{ ElementXPath }}")
{% elseif (ElementSelectedBy == 'ElementId') -%}
  By.id("{{ ElementId }}")
{% elseif (ElementSelectedBy == 'ElementText') -%}
  By.xpath("//{{ ElementTagName }}[contains(normalize-space(text()), '{{ ElementText }}')]")
{% endif -%}
)
// ...
```
```c#
{#
template: Selenium with LINQ C#
#}

// {{ ElementCodeName }}
{% if (ElementText != '') -%}
// {{ ElementText }}
{% endif -%}
IWebElement element =
{% if (ElementSelectedBy == 'ElementText') -%}
  driver.FindElements(By.TagName("{{ ElementTagName }}"))
        .First(o => String.Compare("{{ElementText}}", o.Text, StringComparison.InvariantCulture) == 0);
{% else -%}
  driver.FindElement(
{% if (ElementSelectedBy == 'ElementCssSelector') -%}
  By.CssSelector("{{ ElementCssSelector }}")
{% elseif (ElementSelectedBy == 'ElementXPath') -%}
  By.XPath("{{ ElementXPath }}")
{% elseif (ElementSelectedBy == 'ElementId') -%}
  By.Id("{{ ElementId }}")
{% endif -%}
)
{% endif -%}
)
// ...
```
to produce a standalone program in Java, c#, Python or whatnot
- this part is a work in progress.

### See Also
  * [selenese and side runner Java prject](https://github.com/vmi/selenese-runner-java) Selenium IDE native format (selenese and side) interpreter
  * [Work in progress for IDE TNG](https://github.com/SeleniumHQ/selenium-ide)
  * [Selenium IDE TNG commands for Side recording deserialization](https://github.com/SeleniumHQ/selenium-ide/blob/master/packages/selianize/src/command.js)
  * [selenium-ide issue #222: Can selenium ide add support to export script to html format? NOTE: closed](https://github.com/SeleniumHQ/selenium-ide/issues/222)
  * [SWD by Dmytro Zharii and collaborators](https://github.com/dzharii/swd-recorder)
  * [SWET](https://github.com/sergueik/SWET)
  * [Katalon Studio](https://www.katalon.com/)
  * [Plato-solutions/Siderunner](https://github.com/Plato-solutions/siderunner) - JS library for parsing and running selenium .side files used in a Selenium IDE
  * [Gson Deserialization Cookbook by Eugen Paraschiv of baeldung](https://www.baeldung.com/gson-deserialization-guide )
  * [Selenese and Side script interpreter implemented in Java](https://github.com/vmi/selenese-runner-java)
  * [Selenium IDE](https://applitools.com/blog/why-selenium-ide-2019)
  * [Selenium4j](https://github.com/nextinterfaces/selenium4j) is a tool translating Selenium IDE HTML scripts to Java JUnit tests (development stopped in 2017)
  * [side-runner-py/side-runner-py](https://github.com/side-runner-py/side-runner-py) - Selenium SIDE file runner Python library
  * [selenium-side-runner](https://github.com/nixel2007/docker-selenium-side-runner) SIDE test Docker hosted runner

### License
This project is licensed under the terms of the MIT license.

### Author
[Serguei Kouzmine](kouzmine_serguei@yahoo.com)
[]
