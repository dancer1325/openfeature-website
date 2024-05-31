- Flavors
    - Dynamic Context Paradigm / Server-side SDK
        - uses
            - server applications --
            *Example:* web server / context can contain clientIP, sessionId --
        - Evaluation Context changes frequently
    - Static Context Paradigm / Client-side SDK
        - uses
            - client applications
        - Evaluation Context changes less frequently
        *Example:* — in response to — user actions / UI events
            - context — corresponds to — 1! user
                - context data
                    - — represents — facts about
                        - user
                        - application state
                    - context reconciliation
                        - := if the context data changes → necessary for the provider to
                            - update it’s cache about evaluated flags
                            - request to the server — updated ruleset