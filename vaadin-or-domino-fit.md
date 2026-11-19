# Is Vaadin or Domino UI a natural fit for this project?

_Written 2026-11-19._ See also [domino-ui-vs-vaadin.md](domino-ui-vs-vaadin.md) for a general comparison of the two frameworks.

Domino UI fits. Vaadin doesn't really, because adopting it would mean replacing the project rather than upgrading it.

## What the project is

A roughly 1,000-line GWT quickstart. The client side is Java compiled to JS, with UiBinder views (`MainPanel`, `TodoWidget`), an event bus and a controller that calls the Spring Boot REST endpoint at `/rest/todos` through `RequestBuilder`. The whole point of the project is to show GWT and Spring Boot working together.

## Domino UI: a natural fit

- It runs on GWT 2.10+, and the project is on 2.13.1, so the toolchain, the `gwt-maven-plugin` and the Dev Mode workflow stay the same.
- The architecture stays the same too: a client-side single-page app talking to a stateless REST backend. The event classes, `ModelHandler` and the controller logic can mostly stay.
- The work is mostly confined to the `ui` package. The two UiBinder views and `ImageButton` would be rewritten with Domino's fluent builders, because Domino uses elemental2 elements, not GWT Widgets. Optionally, `RequestBuilder` and `JsonHelper` could be swapped for domino-rest and domino-jackson.
- One caveat: Domino UI 2.0 still looked like a release candidate when `domino-ui-vs-vaadin.md` was written, so check Maven Central before adopting it.

## Vaadin: a good fit for Spring Boot, a poor fit for this project

- The entire `client` package, the `.gwt.xml` module, UiBinder and the GWT build would be deleted.
- The REST endpoint would no longer be needed, since the UI would call the todo service directly on the server.
- The result would be a Spring Boot + Vaadin todo app, which is a perfectly good app but no longer a "spring boot gwt" quickstart. At that point it's cleaner to start a new project from start.vaadin.com.

## Recommendation

For a modern look and components while keeping the project's identity, use Domino UI. That matches the conclusion of [domino-ui-vs-vaadin.md](domino-ui-vs-vaadin.md). Pick Vaadin only after deciding to move off GWT altogether, and then do it as a new project. For an app this small, plain GWT with some CSS is also a valid choice: neither framework adds much to a todo list beyond looks, plus practice with the framework if that's the goal.
