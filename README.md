# cloud-itonami-assoc-9411-prt-cip

Industry rule/history catalog for **CIP - Confederação Empresarial de
Portugal** — the TWENTY-NINTH entry aligned to **ISIC 9411** (activities
of business, employers, and professional membership organizations),
alongside
[`-9411-pol-lewiatan`](https://github.com/cloud-itonami/cloud-itonami-assoc-9411-pol-lewiatan)
(Poland),
[`-9411-gbr-cbi`](https://github.com/cloud-itonami/cloud-itonami-assoc-9411-gbr-cbi)
(United Kingdom),
[`-9411-ury-ciu`](https://github.com/cloud-itonami/cloud-itonami-assoc-9411-ury-ciu)
(Uruguay), and 27 other national industry/employers associations.
Part of the [`cloud-itonami`](https://github.com/cloud-itonami)
compliance-fact family (ADR-2607141700,
`cloud-itonami-compliance-fact-federation`, in `com-junkawasaki/root`).

Portugal has real, individually verified facts across **all three axes**
(country, municipality, association).

## Every entry carries the span it rests on

Each entry records `:association-rule/source-quote` — verbatim text at
its `:association-rule/url`. That field is the point of this catalog, not
decoration:

> **Reachability is not support.** A URL that answers `200` and no longer
> contains the claim looks exactly like one that does. A citation checked
> only for its status code can rot with nothing to show for it.

```bash
nbb scripts/verify-catalog.cljs          # structural only, offline
nbb scripts/verify-catalog.cljs --live   # fetch every citation; check every
                                         # span and every recorded date
```

Exit codes are three-valued on purpose: `0` checked and clean, `1`
findings printed, `2` **REFUSED** — could not check. A run that could not
read its sources must not leave the same trace as one that read them and
found nothing wrong.

## Dates are checked separately from quotes

A quote being present is still not support for the date beside it.
`cip.org.pt` is a **timeline whose dates are year headings standing
outside the sentence** — "Opening of CIP's delegation in Porto." carries
no date at all and would sit equally happily under 1976 or under 1984.
So `:association-rule/date-basis` records where each date is supposed to
be readable from, and `--live` reads it back:

| `:date-basis` | what the verifier checks |
|---|---|
| `:timeline-year-heading` | the **nearest year standing above** the quote in the document is the year recorded |
| `:in-quote-under-year-heading` | day/month named in the span, year from the heading above it |
| `:corroborating-quote` | the date is read out of the corroborating source's span |

This is the check that catches a year carried over from a neighbouring
paragraph — a slip the quote check alone cannot see, because the span
really is on the page; it just does not say what the entry claims.

## Four voices, never merged

`:association-rule/url-provenance` names **who is speaking**, and the
verifier checks the keyword against the URL's host, so a third-party page
cannot quietly acquire first-party authority.

| provenance | host | what it is |
|---|---|---|
| `:official-cip-org-pt` | `cip.org.pt` | CIP about itself |
| `:pt-social-concertation-body` | `ces.pt` | Conselho Económico e Social, the statutory tripartite body |
| `:peak-body-businesseurope` | `businesseurope.eu` | the European peak body CIP belongs to |
| `:wikipedia-corroborated` | `wikipedia.org` | an encyclopaedia, carried only as corroboration |

Where a second source independently states the same fact it is carried as
`:corroborating-url` / `-provenance` / `-quote` rather than folded into
the primary citation. **This matters for precision that only one source
gives**: the founding month (May 1974) and the 1999 honour's day (9 June)
are Wikipedia's, *not* something `cip.org.pt` says — its timeline gives
years. An earlier version of this catalog recorded `1974-05` citing the
official page alone, which cited a page for a precision it does not carry.

## Where a date is not narrowed, it says why

Two agreements are recorded at year precision with
`:date-not-narrowed-because`, rather than picking whichever source looked
more authoritative:

- The CES index prints **"29 de Março"** against most of its older
  agreements — the same day and month repeating across unrelated years —
  so it is the list's default, not a signature date.
- Its 2022 agreement is dated **10 October** on the index while the PDF it
  links is filed `..._9out2022.pdf`. Two statements by the same body, one
  day apart.

## Scope

A **read-only reference/archive** catalog — not an Advisor⊣Governor
actuation actor. It proposes or executes nothing on CIP's behalf.

Coverage is reported honestly (see `association.facts/coverage`): an
association not in `catalog` has **no spec-basis**, full stop — never
fabricate one. `association.facts/unsourced` asks the same question in
production, where `verify-catalog.cljs` cannot run.

**No personal names.** `organization.edn` records the institutional office
title only. CIP's own timeline and the CES composition page both name
individuals; none is persisted here, which is why the 2023 presidential
election appears in neither the catalog nor this README.

## Slug disambiguation note

Ecuador's [`-9411-ecu-cip`](https://github.com/cloud-itonami/cloud-itonami-assoc-9411-ecu-cip)
repo (Cámara de Industrias y Producción de Ecuador) already uses the
bare slug `"cip"`. Portugal's CIP is an entirely unrelated organization
that happens to share the acronym, so this repo deliberately uses the slug
**`cip-prt`** instead, keeping the two CIPs cleanly disambiguated in every
cross-repo DataScript query.

## Data

- `data/datascript-tx.edn` — **source of truth**, DataScript tx-data.
  Query it alongside other `cloud-itonami`/`etzhayyim` compliance-fact
  sources via `com-junkawasaki/root`'s `scripts/compliance-fact-query.cljs`.
- `src/association/facts.cljc` — the catalog as Clojure data.
- `src/association_facts.kotoba` — **generated** by
  `scripts/gen-kotoba-port.cljs`; reaches the Kotoba oracle, wasm and both
  native ISAs, which the `.cljc` cannot. Do not hand-edit —
  `nbb scripts/gen-kotoba-port.cljs --check` will say if someone did.
- `schema/association-rule.edn` — DataScript schema.

`test/association_facts_kotoba_parity_test.clj` compares the `.cljc` and
the port field by field, so the two copies cannot drift apart silently.

## License

AGPL-3.0-or-later (matches the `cloud-itonami-iso3166-*` /
`-municipality-*` / `-assoc-*` / `-lei-*` convention).
