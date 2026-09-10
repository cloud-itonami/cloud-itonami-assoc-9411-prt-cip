(ns association.facts
  "Industry rule/history catalog for CIP -- Confederação Empresarial de
  Portugal -- a 71st industry-association-level source per ADR-2607141700
  (cloud-itonami-compliance-fact-federation), the TWENTY-NINTH entry aligned
  to ISIC 9411 (activities of business, employers, and professional
  membership organizations). Portugal has real, individually verified facts
  across all three axes: country (cloud-itonami-iso3166-prt statute.facts),
  municipality (cloud-itonami-municipality-prt-lisbon), and association
  (this entry).

  SLUG DISAMBIGUATION: Ecuador's -9411-ecu-cip repo (Cámara de Industrias y
  Producción de Ecuador) already uses the bare slug \"cip\". Portugal's CIP is
  an entirely unrelated organization that happens to share the acronym, so
  this repo uses \"cip-prt\" -- otherwise `compliance-fact-query.cljs
  association cip` would silently merge two countries' facts under one key.

  EVERY ENTRY CARRIES THE SPAN IT RESTS ON. `:association-rule/source-quote`
  is verbatim text at `:association-rule/url`. This is not decoration: a URL
  that answers 200 and no longer contains the claim looks exactly like one
  that does, so a citation checked only for reachability can rot with nothing
  to show for it. `scripts/verify-catalog.cljs --live` fetches every citation
  and checks the span; `--live` is the only thing here that can tell a live
  source from a live URL.

  DATES ARE CHECKED SEPARATELY FROM QUOTES, because a quote being present is
  still not support for the date beside it. cip.org.pt is a timeline whose
  dates are year HEADINGS standing outside the sentence -- \"Opening of CIP's
  delegation in Porto.\" carries no date and would sit equally happily under
  1976 or 1984. `:association-rule/date-basis` records where each date is
  supposed to be readable from, and the verifier reads it back:

    :timeline-year-heading         the nearest year standing above the quote
    :in-quote-under-year-heading   day/month in the span, year from the heading
    :corroborating-quote           read out of the corroborating source's span

  FOUR VOICES, NEVER MERGED. `:association-rule/url-provenance` names who is
  speaking -- CIP about itself, the statutory social-concertation body (CES),
  the European peak body, or an encyclopaedia -- and the verifier checks the
  keyword against the URL's host, so a third-party page cannot quietly acquire
  first-party authority. Where a second source independently states the same
  fact, it is carried as `:corroborating-url`/`-provenance`/`-quote` rather
  than folded into the primary citation: the founding month (May 1974) and the
  1999 honour's day (9 June) are Wikipedia's added precision, NOT something
  cip.org.pt says, and recording them against the official page alone would
  have cited a page for a precision it does not carry.

  WHERE A DATE IS NOT NARROWED, IT SAYS WHY. The CES index prints
  \"29 de Março\" against most of its older agreements -- the same day and
  month repeating across unrelated years -- so it is the list's default, not a
  signature date; and its 2022 agreement is dated 10 October on the index
  while the PDF it links is filed `..._9out2022.pdf`. Both are recorded at
  year precision with `:date-not-narrowed-because` saying so, rather than
  picking whichever looked more authoritative.

  An association not in `catalog` has NO spec-basis, full stop; never
  fabricate one."
  (:require [kotoba.lang.text :as str]))

(def catalog
  "association-slug -> vector of association-rule entries.

   Derived from data/datascript-tx.edn, which is also the input to
   scripts/gen-kotoba-port.cljs. test/association_facts_kotoba_parity_test.clj
   compares this map to that port field by field, so the two copies cannot
   drift apart without the suite saying so."
  {"cip-prt"
     [{:association-rule/id "cip-prt.founding-1974-05"
       :association-rule/title "CIP founded in May 1974, as Confederação da Indústria Portuguesa"
       :association-rule/association "cip-prt"
       :association-rule/isic "9411"
       :association-rule/country "PRT"
       :association-rule/kind :governance-program
       :association-rule/url "https://cip.org.pt/en/history/"
       :association-rule/url-provenance :official-cip-org-pt
       :association-rule/source-quote "Foundation of CIP."
       :association-rule/corroborating-url "https://pt.wikipedia.org/wiki/CIP_%E2%80%93_Confedera%C3%A7%C3%A3o_Empresarial_de_Portugal"
       :association-rule/corroborating-provenance :wikipedia-corroborated
       :association-rule/corroborating-quote "fundada em maio de 1974"
       :association-rule/established-date "1974-05"
       :association-rule/date-basis :corroborating-quote
       :association-rule/retrieved-at "2026-09-06"
       :association-rule/topic #{:governance}}
      {:association-rule/id "cip-prt.first-industry-congress-1975"
       :association-rule/title "1st Congress of Portuguese Industry"
       :association-rule/association "cip-prt"
       :association-rule/isic "9411"
       :association-rule/country "PRT"
       :association-rule/kind :governance-program
       :association-rule/url "https://cip.org.pt/en/history/"
       :association-rule/url-provenance :official-cip-org-pt
       :association-rule/source-quote "1st Congress of Portuguese Industry."
       :association-rule/established-date "1975"
       :association-rule/date-basis :timeline-year-heading
       :association-rule/retrieved-at "2026-09-06"
       :association-rule/topic #{:governance}}
      {:association-rule/id "cip-prt.porto-delegation-1976"
       :association-rule/title "CIP opens its Porto delegation"
       :association-rule/association "cip-prt"
       :association-rule/isic "9411"
       :association-rule/country "PRT"
       :association-rule/kind :governance-program
       :association-rule/url "https://cip.org.pt/en/history/"
       :association-rule/url-provenance :official-cip-org-pt
       :association-rule/source-quote "Opening of CIP’s delegation in Porto."
       :association-rule/established-date "1976"
       :association-rule/date-basis :timeline-year-heading
       :association-rule/retrieved-at "2026-09-06"
       :association-rule/topic #{:governance}}
      {:association-rule/id "cip-prt.unice-full-member-1981"
       :association-rule/title "CIP admitted as a full member of UNICE (today BusinessEurope)"
       :association-rule/association "cip-prt"
       :association-rule/isic "9411"
       :association-rule/country "PRT"
       :association-rule/kind :membership
       :association-rule/url "https://cip.org.pt/en/history/"
       :association-rule/url-provenance :official-cip-org-pt
       :association-rule/source-quote "Admission of CIP as full member of UNICE – Union of Industrial and Employers’ Confederation of Europe."
       :association-rule/established-date "1981"
       :association-rule/date-basis :timeline-year-heading
       :association-rule/retrieved-at "2026-09-06"
       :association-rule/topic #{:governance :europe}}
      {:association-rule/id "cip-prt.eesc-representation-1986"
       :association-rule/title "CIP begins representation in the European Economic and Social Committee"
       :association-rule/association "cip-prt"
       :association-rule/isic "9411"
       :association-rule/country "PRT"
       :association-rule/kind :membership
       :association-rule/url "https://cip.org.pt/en/history/"
       :association-rule/url-provenance :official-cip-org-pt
       :association-rule/source-quote "CIP begins its representation in the European Economic and Social Council (EESC) in Brussels."
       :association-rule/established-date "1986"
       :association-rule/date-basis :timeline-year-heading
       :association-rule/retrieved-at "2026-09-06"
       :association-rule/topic #{:governance :europe}}
      {:association-rule/id "cip-prt.cnep-constitution-1990"
       :association-rule/title "Formal constitution of CNEP, with CAP and CCP"
       :association-rule/association "cip-prt"
       :association-rule/isic "9411"
       :association-rule/country "PRT"
       :association-rule/kind :governance-program
       :association-rule/url "https://cip.org.pt/en/history/"
       :association-rule/url-provenance :official-cip-org-pt
       :association-rule/source-quote "Formal constitution of CNEP – National Council of Portuguese Companies"
       :association-rule/established-date "1990"
       :association-rule/date-basis :timeline-year-heading
       :association-rule/retrieved-at "2026-09-06"
       :association-rule/topic #{:governance}}
      {:association-rule/id "cip-prt.economic-social-agreement-1990"
       :association-rule/title "Social Dialogue — Economic and Social Agreement"
       :association-rule/association "cip-prt"
       :association-rule/isic "9411"
       :association-rule/country "PRT"
       :association-rule/kind :tripartite-agreement
       :association-rule/url "https://cip.org.pt/en/history/"
       :association-rule/url-provenance :official-cip-org-pt
       :association-rule/source-quote "Social Dialogue – Economic and Social Agreement."
       :association-rule/established-date "1990"
       :association-rule/date-basis :timeline-year-heading
       :association-rule/retrieved-at "2026-09-06"
       :association-rule/topic #{:governance :labor}}
      {:association-rule/id "cip-prt.order-of-merit-1999-06-09"
       :association-rule/title "CIP made an Honorary Member of the Order of Merit for Agriculture, Commerce and Industry (Industrial class)"
       :association-rule/association "cip-prt"
       :association-rule/isic "9411"
       :association-rule/country "PRT"
       :association-rule/kind :governance-program
       :association-rule/url "https://cip.org.pt/en/history/"
       :association-rule/url-provenance :official-cip-org-pt
       :association-rule/source-quote "25 years of CIP: in this occasion CIP is bestowed with the title of Honorary member of the Agricultural, Commercial and Industrial Order of Merit (Class of Industrial Merit)"
       :association-rule/corroborating-url "https://pt.wikipedia.org/wiki/CIP_%E2%80%93_Confedera%C3%A7%C3%A3o_Empresarial_de_Portugal"
       :association-rule/corroborating-provenance :wikipedia-corroborated
       :association-rule/corroborating-quote "A 9 de Junho de 1999, foi agraciada com o grau de Membro-Honorário da Ordem Civil do Mérito Agrícola, Industrial e Comercial"
       :association-rule/established-date "1999-06-09"
       :association-rule/date-basis :corroborating-quote
       :association-rule/retrieved-at "2026-09-06"
       :association-rule/topic #{:governance}}
      {:association-rule/id "cip-prt.portuguese-business-council-2002-02-19"
       :association-rule/title "Constitution of the Portuguese Business Council, joining CIP, CAP, CCP, AEP and AIP"
       :association-rule/association "cip-prt"
       :association-rule/isic "9411"
       :association-rule/country "PRT"
       :association-rule/kind :governance-program
       :association-rule/url "https://cip.org.pt/en/history/"
       :association-rule/url-provenance :official-cip-org-pt
       :association-rule/source-quote "February 19th, constitution of the Portuguese Business Council, joining CIP, CAP, CCP, AEP and AIP in one organization."
       :association-rule/established-date "2002-02-19"
       :association-rule/date-basis :in-quote-under-year-heading
       :association-rule/retrieved-at "2026-09-06"
       :association-rule/topic #{:governance}}
      {:association-rule/id "cip-prt.tripartite-labour-regulation-2008"
       :association-rule/title "Tripartite agreement on a new system of regulation for labour relations, employment policy and social protection"
       :association-rule/association "cip-prt"
       :association-rule/isic "9411"
       :association-rule/country "PRT"
       :association-rule/kind :tripartite-agreement
       :association-rule/url "https://cip.org.pt/en/history/"
       :association-rule/url-provenance :official-cip-org-pt
       :association-rule/source-quote "Tripartite Agreement for a new System of Regulation for Labour Relations, Employment policies and Social Protection in Portugal."
       :association-rule/corroborating-url "https://ces.pt/concertacao-social/acordos-de-concertacao-social/"
       :association-rule/corroborating-provenance :pt-social-concertation-body
       :association-rule/corroborating-quote "Acordo tripartido para um novo sistema de regulação das relações laborais, das políticas de emprego e da proteção social em Portugal"
       :association-rule/established-date "2008"
       :association-rule/date-basis :timeline-year-heading
       :association-rule/date-not-narrowed-because "The CES index prints 29 de Março against most of its older agreements, including this one; the same day-and-month repeats across unrelated years, so it is the list's default rather than a signature date. Year-only is what two sources agree on."
       :association-rule/retrieved-at "2026-09-06"
       :association-rule/topic #{:governance :labor}}
      {:association-rule/id "cip-prt.renamed-2010"
       :association-rule/title "CIP established under its present name, CIP - Confederação Empresarial de Portugal"
       :association-rule/association "cip-prt"
       :association-rule/isic "9411"
       :association-rule/country "PRT"
       :association-rule/kind :governance-program
       :association-rule/url "https://cip.org.pt/en/history/"
       :association-rule/url-provenance :official-cip-org-pt
       :association-rule/source-quote "CIP is established as CIP – Confederation of Portuguese Business."
       :association-rule/corroborating-url "https://pt.wikipedia.org/wiki/CIP_%E2%80%93_Confedera%C3%A7%C3%A3o_Empresarial_de_Portugal"
       :association-rule/corroborating-provenance :wikipedia-corroborated
       :association-rule/corroborating-quote "assumiu, em 2010, a designação CIP – Confederação Empresarial de Portugal"
       :association-rule/established-date "2010"
       :association-rule/date-basis :corroborating-quote
       :association-rule/retrieved-at "2026-09-06"
       :association-rule/topic #{:governance}}
      {:association-rule/id "cip-prt.tripartite-competitiveness-employment-2011"
       :association-rule/title "Tripartite Agreement for Competitiveness and Employment"
       :association-rule/association "cip-prt"
       :association-rule/isic "9411"
       :association-rule/country "PRT"
       :association-rule/kind :tripartite-agreement
       :association-rule/url "https://cip.org.pt/en/history/"
       :association-rule/url-provenance :official-cip-org-pt
       :association-rule/source-quote "Tripartite Agreement for Competitiveness and Employment."
       :association-rule/established-date "2011"
       :association-rule/date-basis :timeline-year-heading
       :association-rule/retrieved-at "2026-09-06"
       :association-rule/topic #{:governance :labor}}
      {:association-rule/id "cip-prt.mid-term-agreement-2022"
       :association-rule/title "CIP signs the Mid-Term Agreement to improve Incomes, Wages and Competitiveness"
       :association-rule/association "cip-prt"
       :association-rule/isic "9411"
       :association-rule/country "PRT"
       :association-rule/kind :tripartite-agreement
       :association-rule/url "https://cip.org.pt/en/history/"
       :association-rule/url-provenance :official-cip-org-pt
       :association-rule/source-quote "Signature of the Mid-Term Agreement to improve Incomes, Wages and Competitiveness"
       :association-rule/corroborating-url "https://ces.pt/concertacao-social/acordos-de-concertacao-social/"
       :association-rule/corroborating-provenance :pt-social-concertation-body
       :association-rule/corroborating-quote "Acordo de Médio Prazo de Melhoria dos Rendimentos, dos Salários e da Competitividade"
       :association-rule/established-date "2022"
       :association-rule/date-basis :timeline-year-heading
       :association-rule/date-not-narrowed-because "The CES index dates this 10 de Outubro, 2022, but the agreement it links is filed as ...Competitividade_9out2022.pdf. Two statements by the same body, one day apart; neither is quoted here as the day."
       :association-rule/retrieved-at "2026-09-06"
       :association-rule/topic #{:governance :labor}}
      {:association-rule/id "cip-prt.social-concertation-seat"
       :association-rule/title "CIP holds an employers' confederation seat in the Standing Committee for Social Concertation"
       :association-rule/association "cip-prt"
       :association-rule/isic "9411"
       :association-rule/country "PRT"
       :association-rule/kind :membership
       :association-rule/url "https://ces.pt/concertacao-social/composicao/"
       :association-rule/url-provenance :pt-social-concertation-body
       :association-rule/source-quote "Confederações Empresariais CIP - Confederação Empresarial de Portugal"
       :association-rule/date-unknown-because "The CES composition page states the current membership without saying when CIP's seat began, and no date is recorded here that the page does not carry."
       :association-rule/retrieved-at "2026-09-06"
       :association-rule/topic #{:governance :labor}}
      {:association-rule/id "cip-prt.businesseurope-member"
       :association-rule/title "CIP is BusinessEurope's member federation for Portugal"
       :association-rule/association "cip-prt"
       :association-rule/isic "9411"
       :association-rule/country "PRT"
       :association-rule/kind :membership
       :association-rule/url "https://www.businesseurope.eu/members/"
       :association-rule/url-provenance :peak-body-businesseurope
       :association-rule/source-quote "CIP Confederação Empresarial de Portugal"
       :association-rule/date-unknown-because "The members list names the current member per country and carries no admission date; the 1981 UNICE admission is recorded separately from CIP's own timeline."
       :association-rule/retrieved-at "2026-09-06"
       :association-rule/topic #{:governance :europe}}]
})

(defn spec-basis [association] (get catalog association))

(defn coverage
  ([] (coverage (keys catalog)))
  ([associations]
   (let [have (filter catalog associations)
         missing (remove catalog associations)]
     {:requested (count associations)
      :covered (count have)
      :covered-associations (vec (sort have))
      :missing-associations (vec (sort missing))
      :note (str "cloud-itonami-assoc-9411-prt-cip Wave 0 (ADR-2607141700): "
                 (count (get catalog "cip-prt")) " CIP entries, each citing a "
                 "source with the page and the verbatim span it rests on, across "
                 "four voices: CIP itself, the statutory social-concertation body "
                 "(CES), the European peak body, and an encyclopaedia. Extend "
                 "`association.facts/catalog`, never fabricate an id/url.")})))

(defn by-topic [association topic]
  (filterv #(contains? (:association-rule/topic %) topic) (spec-basis association)))

(defn sources
  "Distinct citation URLs, primary and corroborating, in catalog order.

   `coverage` counts entries; this counts the documents behind them. The two
   differ on purpose -- a catalog can grow entries without gaining a source,
   which is exactly the shape a padded citation list has."
  ([] (sources (keys catalog)))
  ([associations]
   (->> (mapcat #(get catalog %) associations)
        (mapcat (juxt :association-rule/url :association-rule/corroborating-url))
        (remove nil?)
        distinct
        vec)))

(defn unsourced
  "Entries whose citation is missing a verbatim span, or whose date has no
   stated basis. Should always be empty; it is a question the catalog can be
   asked in production, where `scripts/verify-catalog.cljs` cannot run."
  ([] (unsourced (keys catalog)))
  ([associations]
   (->> (mapcat #(get catalog %) associations)
        (filterv (fn [e]
                   (or (str/blank? (str (:association-rule/source-quote e)))
                       (and (:association-rule/established-date e)
                            (nil? (:association-rule/date-basis e)))))))))
