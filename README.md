# cloud-itonami-assoc-9411-prt-cip

Industry rule/history catalog for **CIP - Confederação Empresarial de
Portugal** — the TWENTY-NINTH entry aligned to **ISIC 9411** (activities
of business, employers, and professional membership organizations),
alongside
[`-9411-pol-lewiatan`](https://github.com/cloud-itonami/cloud-itonami-assoc-9411-pol-lewiatan)
(Poland),
[`-9411-ury-ciu`](https://github.com/cloud-itonami/cloud-itonami-assoc-9411-ury-ciu)
(Uruguay), and 27 other national industry/employers associations.
Part of the [`cloud-itonami`](https://github.com/cloud-itonami)
compliance-fact family (ADR-2607141700,
`cloud-itonami-compliance-fact-federation`, in `com-junkawasaki/root`).

Fills one of the 4 remaining countries (GTM/HND/PAN/PRT) after Poland
closed at tick 168. Portugal now has real, individually verified
facts across **all three axes** (country, municipality, association).

## Slug disambiguation note

Ecuador's [`-9411-ecu-cip`](https://github.com/cloud-itonami/cloud-itonami-assoc-9411-ecu-cip)
repo (Cámara de Industrias y Producción de Ecuador) already uses the
bare slug `"cip"`. Portugal's CIP is an entirely unrelated
organization that happens to share the acronym, so this repo
deliberately uses the slug **`cip-prt`** instead, keeping the two
CIPs cleanly disambiguated in every cross-repo DataScript query.

## Sourcing note

Both dates are directly confirmed by reading `cip.org.pt`'s own
official `/en/history/` page: the original founding (as Confederação
da Indústria Portuguesa) in 1974, and the organization's
establishment as today's CIP - Confederação Empresarial de Portugal
in 2010. Independently corroborated by `pt.wikipedia.org`, which adds
month precision to the founding (May 1974) and independently confirms
the 2010 renaming.

## Scope

A **read-only reference/archive** catalog — not an Advisor⊣Governor
actuation actor. It proposes or executes nothing on CIP's behalf.

Coverage is reported honestly (see `association.facts/coverage`): an
association not in `catalog` has **no spec-basis**, full stop — never
fabricate one.

## Data

- `src/association/facts.cljc` — the catalog, source of truth.
- `schema/association-rule.edn` — DataScript schema.
- `data/datascript-tx.edn` — derived DataScript tx-data (query this
  alongside other `cloud-itonami`/`etzhayyim` compliance-fact sources via
  `com-junkawasaki/root`'s `scripts/compliance-fact-query.cljs`).

## License

AGPL-3.0-or-later (matches the `cloud-itonami-iso3166-*` /
`-municipality-*` / `-assoc-*` / `-lei-*` convention).
