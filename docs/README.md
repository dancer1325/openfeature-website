- := Specification / Standardization for Feature Flagging
    - characteristics
        - open source (Apache 2) from CNCF in incubating maturity level
        - vendor-agnostic
        - community-driven API
        - integrated with popular open source projects
        - support multi languages
    - requirements
        - ⚠️ full Feature Flag system == configure a [provider](https://www.notion.so/Ecosystem-205885330e68444281fe1a9646d58451?pvs=21)⚠️
          **Note:** 👁️NO change about hot to evaluate flags 👁️

- Feature Flag
    - := software development technique / 👁️without modifying source code 👁️ → enable OR disable OR change — behavior of certain features OR code paths
    - == if / else statement — can be controlled — at runtime
    - allows
      - reducing time-life of feature branches
      - hiding work-in-progress functionality from end users
      - performing canary releases & A/B testing
        - == roll out a feature / small subset of users
      - degrading parts of a production system
      - restricting access — based on — user characteristics’
        - geographically
        - IP address
        - license reasons

- Feature Flagging System
  - == Feature Flagging Service + Feature Flagging Client + Use of Feature Flag
    - Feature Flagging Client
      - == SDK
        - provided by Open Feature
        - plugged into several 3ª party feature flagging providers

## Notes
* https://openfeature.dev/