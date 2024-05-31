- == part / used by an application author
- allows
    - developers can
        - evaluate feature flags
          - basic evaluation — `getBooleanValue(), getStringValue(), getIntegerValue(), getFloatValue(), getObjectValue()` —
            - 👁️if there is some error evaluating → default value is returned 👁️
          - detailed evaluation — `getBooleanDetails(), getStringDetails(), getIntegerDetails(), getFloatDetails(), getObjectDetails()` —
            - returns
              - flag evaluated + additional metadata
           ```
           {
             flagKey: ..
             value: ...
             reason: ...
             variant: ... 
             errorCode: ...
             errorMessage: ...
            }
           ```
        - based on them — to impact — application
- == framework
- prerequisites
  - set a provider