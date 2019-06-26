## More reliable links with Fastparse

TL;DR: We created our own little language to both ensure we our ads have valid links, and make it easier for our users to specify the links.

## Background

We're run ads in many many places, like, hundreds of thousands! Manually creating and maintaing these ads would be a collosal amount of work, especially when you want to do something like rebrand from VRBO or HomeAway to Vrbo 😉. Thankfully, we use a system called rocket to generate our Google Ads based on templates which our SEM team maintains. 

In our templates, our users specify which page the ad goes; the **SE**arch **R**esults **P**age (SERP), **S**earch **L**anding **P**age (SLP), or **L**ist **Y**our **P**roperty page (LYP).

From that, rocket generated a link to the relevant

|Ad Link Type|URL Mod|
|----|---|
|SERP|/keywords:~destination~?petIncluded=false|


