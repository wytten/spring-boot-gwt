# Domino UI 2.x vs Vaadin 25.x

_Written 2026-11-19._

**Versions:** Domino UI 2.0 has been in release candidates for a while. RC2 came out around late 2025, and one listing gives RC4 as the latest. A stable 2.0.0 release was not confirmed, so check Maven Central before committing to it. Vaadin is on its 25 line, with 25.3.x the latest found; 25.0 shipped around December 2025.

## Architecture

| | **Domino UI 2.x** | **Vaadin Flow 25** |
|---|---|---|
| Where the UI runs | In the browser. Java is compiled to JS with GWT 2.10+ or J2CL ("GWT3/J2CL compatible"). | On the server. Java holds the UI state and the browser renders web components (Lit-based in 25). |
| Backend coupling | None. Any API works; DominoKit adds domino-rest and domino-jackson for typed REST and JSON shared between client and server. | Tight. UI code calls services directly in the same JVM, usually Spring Boot. |
| Server cost per user | ~0, since the UI is static files | A session per user holding the component tree |
| Latency sensitivity | Low, because interactions are local | Higher, because most events make a server round trip |
| Offline/PWA | Natural fit | Limited. Hilla (React + TS) is Vaadin's answer for client-heavy apps. |

## Developer experience

| | **Domino UI 2.x** | **Vaadin 25** |
|---|---|---|
| API style | Fluent builders over elemental2, e.g. `Button.create("Save").addClickListener(...)` | Classic component objects, e.g. `new Button("Save", e -> ...)` |
| Usable Java libraries | Only GWT/J2CL-compatible ones, a subset of the JRE with no reflection | The whole JVM ecosystem |
| Build | GWT compile or J2CL via Closure. Slower builds and a more niche toolchain. | Maven or Gradle with Spring Boot and a Vite frontend; 25.1 simplified the build |
| Debugging | Browser devtools with source maps | IDE Java debugger, plus hotswap |
| Theming | Its own design system with CSS variables. v2 dropped the old Bootstrap/jQuery-era CSS. | New default theme Aura in 25 (Lumo still available), styled with CSS custom properties |
| Extra tooling | domino-history (routing), domino-mvp, domino-cli scaffolding | Copilot, designer tooling, and AI features for Grid, Charts and Forms (25.2/25.3), several of them commercial |

## Ecosystem and risk

| | **Domino UI** | **Vaadin** |
|---|---|---|
| Backing | Small DominoKit team | A company, with paid support and long-term-support releases |
| License | Apache 2.0, all free | Core is Apache 2.0. Pro components (Charts, Grid Pro, CRUD and others), Copilot and extended maintenance are paid. Free maintenance for Vaadin 24 ended in June 2026. |
| Community | Small: Gitter, GitHub issues, the GWT Google Group | Large: forum, docs, add-on directory, webinars |
| Long-term bet | Tied to GWT and J2CL, which still get maintained but have a small user base | Mainstream for enterprise Java, with a predictable release cadence |

## Bottom line

- **Pick Domino UI** if you want a fully client-side Java single-page app with a stateless, horizontally scalable backend. It's also the better fit if you already run GWT, need offline support, or want an all-Apache stack. Be ready to depend on a small community and a niche compiler toolchain, and its 2.0 is still a release candidate.
- **Pick Vaadin** for business or CRUD apps where developer speed, direct access to the JVM and Spring, server-side security, and commercial support matter more than cost per user or tolerance of network latency.

For a Java team building internal tools, Vaadin is the safer default. Domino UI is the stronger choice when the architecture needs a client-side app, or when modernizing an existing GWT codebase (like this one).

## Sources

- [Domino UI on GitHub (via awesome.ecosyste.ms)](https://awesome.ecosyste.ms/projects/github.com%2FDominoKit%2Fdomino-ui)
- [Domino UI on Kandi](https://kandi.openweaver.com/java/DominoKit/domino-ui)
- [DominoKit 1.0 announcement](https://groups.google.com/g/google-web-toolkit/c/zyINBdLrO30)
- [GWT Google Group](https://groups.google.com/g/Google-Web-Toolkit)
- [Vaadin roadmap](https://vaadin.com/roadmap) / [mirror](https://origin.vaadin.com/roadmap)
- [What's coming in Vaadin 25](https://blog.vaadin.com/whats-coming-in-vaadin-25)
- [Vaadin 25 beta](https://blog.vaadin.com/vaadin-25-beta-testers-wanted)
- [Vaadin 25.1 (DevBytes)](https://devbytes.co.in/news/vaadin-releases-v251-and-urges-users-to-upgrade-from-v24-before-june-2026)
- [Vaadin 25.2 webinar](https://vaadin.com/forum/t/live-today-vaadin-25-2-release-webinar/179615)
- [Vaadin 24 maintenance end](https://blog.vaadin.com/vaadin-24-free-maintenance-ends-june-16-choose-your-path-forward)
