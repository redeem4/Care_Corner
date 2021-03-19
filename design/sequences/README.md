# Care Corner Sequence Diagrams

Sequence diagrams are a popular dynamic modeling solution in UML because they
specifically focus on lifelines, or the processes and objects that live
simultaneously, and the messages exchanged between them to perform a function
before the lifeline ends.

## Mermaid

Mermaid lets you represent diagrams using text and code. This simplifies
maintaining complex diagrams. It is a Javascript based diagramming and
charting tool that renders Markdown-inspired text definitions to create
and modify diagrams dynamically. The main purpose of Mermaid is to help
Documentation catch up with Development.

https://mermaid-js.github.io/mermaid/

Mermaid Sequence Diagrams: https://mermaid-js.github.io/mermaid/#/sequenceDiagram


Easy ways to write and render mermaid:

* Live Editor: https://mermaid-js.github.io/mermaid-live-editor/
* Visual Code: There are several extensions that make it easy to edit and preview.
* Command Line: https://github.com/mermaid-js/mermaid-cli


## Setup

Use `yarn` or `npm` to install the mermaid cli:

        yarn install


To convert Mermaid mmd diagram to a svg file, run this command:

        npx mmdc -i input.md -o images/output.svg

To convert Mermaid mmd diagram to a png file, run this command:

        npx mmdc -i input.md -o images/output.png
