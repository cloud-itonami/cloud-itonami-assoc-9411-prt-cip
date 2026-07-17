(ns association.facts
  "Industry rule/history catalog for CIP -- Confederação Empresarial de
  Portugal -- a 71st industry-association-level source (see
  cloud-itonami-assoc-9411-sau-fsc, -9411-aut-wko, -9411-irl-ibec,
  -9411-nzl-businessnz, -9411-cze-spcr, -9411-ind-cii, -9411-zaf-busa,
  -9411-bra-cni, -9411-ken-kam, -9411-can-chamber, -9411-mex-coparmex,
  -9411-ita-confindustria, -9411-nld-vnoncw, -9411-kor-kcci,
  -9411-arg-uia, -9411-bel-feb, -9411-dnk-di, -9411-swe-sn, -9411-fin-ek,
  -9411-tha-fti, -9411-chl-sofofa, -9411-col-andi, -9411-cri-uccaep,
  -9411-ecu-cip, -9411-egy-fei, -9411-pry-uip, -9411-ury-ciu,
  -9411-pol-lewiatan for the first twenty-eight) per ADR-2607141700
  (cloud-itonami-compliance-fact-federation). The TWENTY-NINTH entry
  aligned to ISIC 9411 (activities of business, employers, and
  professional membership organizations). Fills Portugal's
  previously-open association-axis gap -- one of the 4 remaining
  countries (GTM/HND/PAN/PRT) after Poland closed at tick 168.
  Portugal now has real, individually verified facts across ALL THREE
  axes (country: cloud-itonami-iso3166-prt statute.facts, pre-
  existing; municipality: cloud-itonami-municipality-prt-lisbon,
  added tick 164; association: this entry).

  NOTE ON SLUG DISAMBIGUATION: Ecuador's -9411-ecu-cip repo
  (Cámara de Industrias y Producción de Ecuador) already uses the
  bare slug \"cip\" for its :association-rule/association value and
  as its catalog map key. Portugal's CIP is an entirely unrelated
  organization that happens to share the same acronym. Using \"cip\"
  here too would make `compliance-fact-query.cljs association cip`
  silently merge two unrelated countries' facts under one query key
  -- so this repo deliberately uses the slug \"cip-prt\" instead
  (catalog key, :association-rule/association value, and the id
  prefix below), keeping the two CIPs cleanly disambiguated in every
  cross-repo query.

  Both dates directly confirmed by reading cip.org.pt's own official
  '/en/history/' page: the original 'Foundation of CIP' (as
  Confederação da Indústria Portuguesa) in 1974 (year-only precision
  on the official page itself); and the organization's establishment
  as today's 'CIP - Confederation of Portuguese Business' in 2010
  (year-only precision, with the new organization's first president
  elected in 2011). Independently corroborated by pt.wikipedia.org,
  which adds month precision to the founding ('founded in maio de
  1974' -- May 1974) and independently confirms the 2010 renaming
  ('Inicialmente fundada como CIP – Confederação da Indústria
  Portuguesa, assumiu, em 2010, a designação CIP – Confederação
  Empresarial de Portugal'). The catalog below uses the
  Wikipedia-refined '1974-05' month precision for the founding
  (rather than bare '1974') since the two sources agree and Wikipedia
  supplies the extra precision without contradicting the primary
  source; the 2010 renaming is used at year-only precision since
  neither source narrows it further.

  An association not in `catalog` has NO spec-basis, full stop; never
  fabricate one.")

(def catalog
  "association-slug -> vector of association-rule entries."
  {"cip-prt"
   [{:association-rule/id "cip-prt.founding-1974-05"
     :association-rule/title "Confederação da Indústria Portuguesa (CIP's original name) founded May 1974"
     :association-rule/association "cip-prt"
     :association-rule/isic "9411"
     :association-rule/country "PRT"
     :association-rule/kind :governance-program
     :association-rule/url "https://cip.org.pt/en/history/"
     :association-rule/url-provenance :official-cip-org-pt
     :association-rule/established-date "1974-05"
     :association-rule/retrieved-at "2026-07-18"
     :association-rule/topic #{:governance}}
    {:association-rule/id "cip-prt.renamed-2010"
     :association-rule/title "Organization established as today's CIP - Confederação Empresarial de Portugal (Confederation of Portuguese Business) in 2010"
     :association-rule/association "cip-prt"
     :association-rule/isic "9411"
     :association-rule/country "PRT"
     :association-rule/kind :governance-program
     :association-rule/url "https://cip.org.pt/en/history/"
     :association-rule/url-provenance :official-cip-org-pt
     :association-rule/established-date "2010"
     :association-rule/retrieved-at "2026-07-18"
     :association-rule/topic #{:governance}}]})

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
                 (count (get catalog "cip-prt")) " CIP entries seeded "
                 "with cip.org.pt official history page + pt.wikipedia.org corroboration. "
                 "Extend `association.facts/catalog`, never fabricate an id/url.")})))

(defn by-topic [association topic]
  (filterv #(contains? (:association-rule/topic %) topic) (spec-basis association)))
