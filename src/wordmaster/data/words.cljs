(ns wordmaster.data.words
  "The 1000 most frequent English words, in strict frequency order.

   Source: Google 10,000 English list (first20hours/google-10000-english),
   frequency-ordered from Google's Trillion Word Corpus, truncated to
   the first 1000. Meanings primarily from WordNet (Princeton, via NLTK,
   most-frequent sense by SemCor lemma count); words WordNet does not
   cover (function words) come from the free Dictionary API
   (dictionaryapi.dev, Wiktionary data) or curated fallbacks in
   scripts/fallbacks.json. Regenerate with:
   NLTK_DATA=/tmp/nltk_data /tmp/wordnet-venv/bin/python scripts/build_words.py")

(def words
  [   {:id 1 :word "the" :meaning "used before a noun to refer to a specific person or thing that is already known" :rank 1}
   {:id 2 :word "of" :meaning "expressing the relationship between a part and a whole, or possession" :rank 2}
   {:id 3 :word "and" :meaning "used to connect words or parts of a sentence" :rank 3}
   {:id 4 :word "to" :meaning "in the direction of; towards a place, person, or result" :rank 4}
   {:id 5 :word "a" :meaning "used before a noun to refer to one person or thing, not a specific one" :rank 5}
   {:id 6 :word "in" :meaning "inside a place, or during a period of time" :rank 6}
   {:id 7 :word "for" :meaning "intended to belong to or be used by someone; because of" :rank 7}
   {:id 8 :word "is" :meaning "third-person singular of be; used to say what something is" :rank 8}
   {:id 9 :word "on" :meaning "physically touching and supported by a surface; during a particular day" :rank 9}
   {:id 10 :word "that" :meaning "used to point to a specific thing already mentioned, or to introduce a clause" :rank 10}
   {:id 11 :word "by" :meaning "used to say who or what does something, or near something" :rank 11}
   {:id 12 :word "this" :meaning "used to point to a person or thing near you or just mentioned" :rank 12}
   {:id 13 :word "with" :meaning "together with; having or using something" :rank 13}
   {:id 14 :word "i" :meaning "used by a speaker to refer to himself or herself" :rank 14}
   {:id 15 :word "you" :meaning "used to refer to the person or people being spoken to" :rank 15}
   {:id 16 :word "it" :meaning "used to refer to a thing, animal, or idea already mentioned" :rank 16}
   {:id 17 :word "not" :meaning "used to make a word or sentence negative" :rank 17}
   {:id 18 :word "or" :meaning "used to connect two or more possibilities" :rank 18}
   {:id 19 :word "be" :meaning "to exist; used to say what something is or how it is" :rank 19}
   {:id 20 :word "are" :meaning "plural and second-person singular of be" :rank 20}
   {:id 21 :word "from" :meaning "indicating where something starts or comes from" :rank 21}
   {:id 22 :word "at" :meaning "used to say where something is, or when something happens" :rank 22}
   {:id 23 :word "as" :meaning "to the same degree; while; in the role of" :rank 23}
   {:id 24 :word "your" :meaning "belonging to you" :rank 24}
   {:id 25 :word "all" :meaning "the whole of something; every one of a group" :rank 25}
   {:id 26 :word "have" :meaning "have or possess, either in a concrete or an abstract sense" :rank 26}
   {:id 27 :word "new" :meaning "not existing before; recently made or discovered" :rank 27}
   {:id 28 :word "more" :meaning "a greater amount or number" :rank 28}
   {:id 29 :word "an" :meaning "used before a noun beginning with a vowel sound, to refer to one person or thing, not a specific one" :rank 29}
   {:id 30 :word "was" :meaning "past tense of be (he, she, or it)" :rank 30}
   {:id 31 :word "we" :meaning "used by a speaker to refer to himself or herself and other people" :rank 31}
   {:id 32 :word "will" :meaning "used to talk about the future, or to say someone is willing to do something" :rank 32}
   {:id 33 :word "home" :meaning "the place where you live" :rank 33}
   {:id 34 :word "can" :meaning "to be able to do something" :rank 34}
   {:id 35 :word "us" :meaning "used to refer to the speaker and other people as the object of a verb" :rank 35}
   {:id 36 :word "about" :meaning "concerning something; approximately" :rank 36}
   {:id 37 :word "if" :meaning "used to introduce a condition" :rank 37}
   {:id 38 :word "page" :meaning "one side of one leaf (of a book or magazine or newspaper or letter etc.) or the written or pictorial matter it contains" :rank 38}
   {:id 39 :word "my" :meaning "belonging to me" :rank 39}
   {:id 40 :word "has" :meaning "third-person singular of have" :rank 40}
   {:id 41 :word "search" :meaning "the activity of looking thoroughly in order to find something or someone" :rank 41}
   {:id 42 :word "free" :meaning "not controlled or limited; costing nothing" :rank 42}
   {:id 43 :word "but" :meaning "used to introduce something that contrasts with what was said before" :rank 43}
   {:id 44 :word "our" :meaning "belonging to us" :rank 44}
   {:id 45 :word "one" :meaning "used of a single unit or thing" :rank 45}
   {:id 46 :word "other" :meaning "not the same one or ones already mentioned or implied" :rank 46}
   {:id 47 :word "do" :meaning "to perform an action or activity" :rank 47}
   {:id 48 :word "no" :meaning "used to give a negative answer; not any" :rank 48}
   {:id 49 :word "information" :meaning "a message received and understood" :rank 49}
   {:id 50 :word "time" :meaning "an instance or single occasion for some event" :rank 50}
   {:id 51 :word "they" :meaning "used to refer to a group of people or things already mentioned" :rank 51}
   {:id 52 :word "site" :meaning "the piece of land on which something is located (or is to be located)" :rank 52}
   {:id 53 :word "he" :meaning "used to refer to a man or boy already mentioned" :rank 53}
   {:id 54 :word "up" :meaning "spatially or metaphorically from a lower to a higher position" :rank 54}
   {:id 55 :word "may" :meaning "used to say that something is possible, or to ask permission" :rank 55}
   {:id 56 :word "what" :meaning "used to ask for information about something" :rank 56}
   {:id 57 :word "which" :meaning "(interrogative) What one or ones (of those mentioned or implied)." :rank 57}
   {:id 58 :word "their" :meaning "Belonging to, from, of, or relating to, them (plural)." :rank 58}
   {:id 59 :word "news" :meaning "information about recent and important events" :rank 59}
   {:id 60 :word "out" :meaning "away from home" :rank 60}
   {:id 61 :word "use" :meaning "put into service" :rank 61}
   {:id 62 :word "any" :meaning "some, no matter which one; at all" :rank 62}
   {:id 63 :word "there" :meaning "in or at that place" :rank 63}
   {:id 64 :word "see" :meaning "perceive by sight or have the power to perceive by sight" :rank 64}
   {:id 65 :word "only" :meaning "no more than; just one thing and nothing else" :rank 65}
   {:id 66 :word "so" :meaning "to a very great extent or degree" :rank 66}
   {:id 67 :word "his" :meaning "belonging to a man or boy already mentioned" :rank 67}
   {:id 68 :word "when" :meaning "at the time that something happens" :rank 68}
   {:id 69 :word "contact" :meaning "close interaction" :rank 69}
   {:id 70 :word "here" :meaning "in or at this place" :rank 70}
   {:id 71 :word "business" :meaning "a commercial or industrial enterprise and the people who constitute it" :rank 71}
   {:id 72 :word "who" :meaning "used to ask which person, or to refer to a person" :rank 72}
   {:id 73 :word "web" :meaning "the internet; a network of connected pages online" :rank 73}
   {:id 74 :word "also" :meaning "in addition" :rank 74}
   {:id 75 :word "now" :meaning "at the present time" :rank 75}
   {:id 76 :word "help" :meaning "give help or assistance" :rank 76}
   {:id 77 :word "get" :meaning "come into the possession of something concrete or abstract" :rank 77}
   {:id 78 :word "pm" :meaning "used after a time to show it is between noon and midnight" :rank 78}
   {:id 79 :word "view" :meaning "a way of regarding situations or topics etc." :rank 79}
   {:id 80 :word "online" :meaning "connected to the internet" :rank 80}
   {:id 81 :word "c" :meaning "the third letter of the English alphabet" :rank 81}
   {:id 82 :word "e" :meaning "the fifth letter of the English alphabet" :rank 82}
   {:id 83 :word "first" :meaning "preceding all others in time or space or degree" :rank 83}
   {:id 84 :word "am" :meaning "first-person singular of be (I am)" :rank 84}
   {:id 85 :word "been" :meaning "past participle of be" :rank 85}
   {:id 86 :word "would" :meaning "Something that would happen, or would be the case, under different circumstances; a potentiality." :rank 86}
   {:id 87 :word "how" :meaning "by what means; in what way" :rank 87}
   {:id 88 :word "were" :meaning "past tense of be (we, you, or they)" :rank 88}
   {:id 89 :word "me" :meaning "used to refer to the speaker as the object of a verb" :rank 89}
   {:id 90 :word "s" :meaning "the nineteenth letter of the English alphabet; also used to form plurals and possessives" :rank 90}
   {:id 91 :word "services" :meaning "performance of duties or provision of space and equipment helpful to others" :rank 91}
   {:id 92 :word "some" :meaning "an unspecified amount or number of something" :rank 92}
   {:id 93 :word "these" :meaning "The thing, item, etc. being indicated." :rank 93}
   {:id 94 :word "click" :meaning "to press a computer mouse button" :rank 94}
   {:id 95 :word "its" :meaning "belonging to a thing already mentioned" :rank 95}
   {:id 96 :word "like" :meaning "prefer or wish to do something" :rank 96}
   {:id 97 :word "service" :meaning "work done by one person or group that benefits another" :rank 97}
   {:id 98 :word "x" :meaning "the twenty-fourth letter of the English alphabet; used to mean an unknown number or thing" :rank 98}
   {:id 99 :word "than" :meaning "used to compare two things" :rank 99}
   {:id 100 :word "find" :meaning "come upon, as if by accident" :rank 100}
   {:id 101 :word "price" :meaning "the property of having material worth (often indicated by the amount of money something would bring if sold)" :rank 101}
   {:id 102 :word "date" :meaning "the specified day of the month" :rank 102}
   {:id 103 :word "back" :meaning "in or to or toward a former location" :rank 103}
   {:id 104 :word "top" :meaning "the upper part of anything" :rank 104}
   {:id 105 :word "people" :meaning "(plural) any group of human beings (men or women or children) collectively" :rank 105}
   {:id 106 :word "had" :meaning "past tense of have" :rank 106}
   {:id 107 :word "list" :meaning "a database containing an ordered array of items (names or topics)" :rank 107}
   {:id 108 :word "name" :meaning "a language unit by which a person or thing is known" :rank 108}
   {:id 109 :word "just" :meaning "only; exactly; a very short time ago" :rank 109}
   {:id 110 :word "over" :meaning "at or to a point across intervening space etc." :rank 110}
   {:id 111 :word "state" :meaning "the condition of a person or thing at a particular time" :rank 111}
   {:id 112 :word "year" :meaning "a period of time containing 365 (or 366) days" :rank 112}
   {:id 113 :word "day" :meaning "time for Earth to make a complete rotation on its axis" :rank 113}
   {:id 114 :word "into" :meaning "To or towards the inside of." :rank 114}
   {:id 115 :word "email" :meaning "a system for sending messages electronically" :rank 115}
   {:id 116 :word "two" :meaning "being one more than one" :rank 116}
   {:id 117 :word "health" :meaning "a healthy state of wellbeing free from disease" :rank 117}
   {:id 118 :word "n" :meaning "the fourteenth letter of the English alphabet" :rank 118}
   {:id 119 :word "world" :meaning "everything that exists anywhere" :rank 119}
   {:id 120 :word "re" :meaning "about; concerning (used in subject lines and references)" :rank 120}
   {:id 121 :word "next" :meaning "nearest in space or position" :rank 121}
   {:id 122 :word "used" :meaning "employed in accomplishing something" :rank 122}
   {:id 123 :word "go" :meaning "change location" :rank 123}
   {:id 124 :word "b" :meaning "the second letter of the English alphabet" :rank 124}
   {:id 125 :word "work" :meaning "activity directed toward making or doing something" :rank 125}
   {:id 126 :word "last" :meaning "immediately past" :rank 126}
   {:id 127 :word "most" :meaning "the greatest amount or number; almost all" :rank 127}
   {:id 128 :word "products" :meaning "commodities offered for sale" :rank 128}
   {:id 129 :word "music" :meaning "an artistic form of auditory communication incorporating instrumental or vocal tones in a structured and continuous manner" :rank 129}
   {:id 130 :word "buy" :meaning "obtain by purchase" :rank 130}
   {:id 131 :word "data" :meaning "a collection of facts from which conclusions may be drawn" :rank 131}
   {:id 132 :word "make" :meaning "to create or produce something" :rank 132}
   {:id 133 :word "them" :meaning "used to refer to a group of people or things as the object of a verb" :rank 133}
   {:id 134 :word "should" :meaning "used to say what is right, expected, or likely" :rank 134}
   {:id 135 :word "product" :meaning "commodities offered for sale" :rank 135}
   {:id 136 :word "system" :meaning "a set of connected things that work together" :rank 136}
   {:id 137 :word "post" :meaning "a message or article published on a website" :rank 137}
   {:id 138 :word "her" :meaning "belonging to a woman or girl; used to refer to a woman as the object of a verb" :rank 138}
   {:id 139 :word "city" :meaning "a large and densely populated urban area" :rank 139}
   {:id 140 :word "t" :meaning "a base found in DNA (but not in RNA) and derived from pyrimidine" :rank 140}
   {:id 141 :word "add" :meaning "make an addition (to)" :rank 141}
   {:id 142 :word "policy" :meaning "a plan of action adopted by an individual or social group" :rank 142}
   {:id 143 :word "number" :meaning "the property possessed by a sum or total or indefinite quantity of units or individuals" :rank 143}
   {:id 144 :word "such" :meaning "of the type already mentioned; so great" :rank 144}
   {:id 145 :word "please" :meaning "used to make a polite request" :rank 145}
   {:id 146 :word "available" :meaning "obtainable or accessible and ready for use or service" :rank 146}
   {:id 147 :word "copyright" :meaning "a document granting exclusive right to publish and sell literary or musical or artistic work" :rank 147}
   {:id 148 :word "support" :meaning "give moral or psychological support, aid, or courage to" :rank 148}
   {:id 149 :word "message" :meaning "a communication (usually brief) that is written or spoken or signaled" :rank 149}
   {:id 150 :word "after" :meaning "happening at a time subsequent to a reference time" :rank 150}
   {:id 151 :word "best" :meaning "(superlative of `good') having the most positive qualities" :rank 151}
   {:id 152 :word "software" :meaning "(computer science) written programs or procedures or rules and associated documentation pertaining to the operation of a computer system and that are stored in read/write memory" :rank 152}
   {:id 153 :word "then" :meaning "subsequently or soon afterward (often used as sentence connectors)" :rank 153}
   {:id 154 :word "jan" :meaning "the first month of the year" :rank 154}
   {:id 155 :word "good" :meaning "having desirable or positive qualities especially those suitable for a thing specified" :rank 155}
   {:id 156 :word "video" :meaning "a recording of moving images" :rank 156}
   {:id 157 :word "well" :meaning "in a good way; in good health" :rank 157}
   {:id 158 :word "d" :meaning "the fourth letter of the English alphabet" :rank 158}
   {:id 159 :word "where" :meaning "The place in which something happens." :rank 159}
   {:id 160 :word "info" :meaning "short for information; facts about something" :rank 160}
   {:id 161 :word "rights" :meaning "an abstract idea of that which is due to a person or governmental body by law or tradition or nature" :rank 161}
   {:id 162 :word "public" :meaning "not private" :rank 162}
   {:id 163 :word "books" :meaning "a written work or composition that has been published (printed on pages bound together)" :rank 163}
   {:id 164 :word "high" :meaning "greater than normal in degree or intensity or amount" :rank 164}
   {:id 165 :word "school" :meaning "an educational institution" :rank 165}
   {:id 166 :word "through" :meaning "from beginning to end" :rank 166}
   {:id 167 :word "m" :meaning "the thirteenth letter of the English alphabet" :rank 167}
   {:id 168 :word "each" :meaning "to or from every one of two or more (considered individually)" :rank 168}
   {:id 169 :word "links" :meaning "words or images on a web page that you click to go to another page" :rank 169}
   {:id 170 :word "she" :meaning "used to refer to a woman or girl already mentioned" :rank 170}
   {:id 171 :word "review" :meaning "look at again" :rank 171}
   {:id 172 :word "years" :meaning "plural of year; periods of 365 days" :rank 172}
   {:id 173 :word "order" :meaning "give instructions to or direct somebody to do something with authority" :rank 173}
   {:id 174 :word "very" :meaning "to a great degree; extremely" :rank 174}
   {:id 175 :word "privacy" :meaning "the quality of being secluded from the presence or view of others" :rank 175}
   {:id 176 :word "book" :meaning "a written work or composition that has been published (printed on pages bound together)" :rank 176}
   {:id 177 :word "items" :meaning "a distinct part that can be specified separately in a group of things that could be enumerated on a list" :rank 177}
   {:id 178 :word "company" :meaning "an institution created to conduct business" :rank 178}
   {:id 179 :word "r" :meaning "the eighteenth letter of the English alphabet" :rank 179}
   {:id 180 :word "read" :meaning "interpret something that is written or printed" :rank 180}
   {:id 181 :word "group" :meaning "any number of entities (members) considered as a unit" :rank 181}
   {:id 182 :word "sex" :meaning "activities associated with sexual intercourse" :rank 182}
   {:id 183 :word "need" :meaning "require as useful, just, or proper" :rank 183}
   {:id 184 :word "many" :meaning "a large number of people or things" :rank 184}
   {:id 185 :word "user" :meaning "a person who makes use of a thing" :rank 185}
   {:id 186 :word "said" :meaning "past tense of say" :rank 186}
   {:id 187 :word "de" :meaning "a word meaning of or from in French, German, and other languages" :rank 187}
   {:id 188 :word "does" :meaning "third-person singular of do" :rank 188}
   {:id 189 :word "set" :meaning "put into a certain place or abstract location" :rank 189}
   {:id 190 :word "under" :meaning "located below or beneath something else" :rank 190}
   {:id 191 :word "general" :meaning "applying to all or most members of a category or group" :rank 191}
   {:id 192 :word "research" :meaning "systematic investigation to establish facts" :rank 192}
   {:id 193 :word "university" :meaning "the body of faculty and students at a university" :rank 193}
   {:id 194 :word "january" :meaning "the first month of the year" :rank 194}
   {:id 195 :word "mail" :meaning "send via the postal service" :rank 195}
   {:id 196 :word "full" :meaning "containing as much or as many as is possible or normal" :rank 196}
   {:id 197 :word "map" :meaning "a diagrammatic representation of the earth's surface (or part of it)" :rank 197}
   {:id 198 :word "reviews" :meaning "a new appraisal or evaluation" :rank 198}
   {:id 199 :word "program" :meaning "a series of steps to be carried out or goals to be accomplished" :rank 199}
   {:id 200 :word "life" :meaning "a characteristic state or mode of living" :rank 200}
   {:id 201 :word "know" :meaning "be cognizant or aware of a fact or a specific piece of information" :rank 201}
   {:id 202 :word "games" :meaning "a contest with rules to determine a winner" :rank 202}
   {:id 203 :word "way" :meaning "how something is done or how it happens" :rank 203}
   {:id 204 :word "days" :meaning "the time during which someone's life continues" :rank 204}
   {:id 205 :word "management" :meaning "the act of managing something" :rank 205}
   {:id 206 :word "p" :meaning "the sixteenth letter of the English alphabet" :rank 206}
   {:id 207 :word "part" :meaning "something determined in relation to something that includes it" :rank 207}
   {:id 208 :word "could" :meaning "(auxiliary verb, defective) To know how to; to be able to." :rank 208}
   {:id 209 :word "great" :meaning "relatively large in size or number or extent" :rank 209}
   {:id 210 :word "united" :meaning "characterized by unity" :rank 210}
   {:id 211 :word "hotel" :meaning "a building where travelers can pay for lodging and meals and other services" :rank 211}
   {:id 212 :word "real" :meaning "being or occurring in fact or actuality" :rank 212}
   {:id 213 :word "f" :meaning "the sixth letter of the English alphabet" :rank 213}
   {:id 214 :word "item" :meaning "a distinct part that can be specified separately in a group of things that could be enumerated on a list" :rank 214}
   {:id 215 :word "international" :meaning "concerning or belonging to all or at least two or more nations" :rank 215}
   {:id 216 :word "center" :meaning "an area that is approximately central within some larger region" :rank 216}
   {:id 217 :word "ebay" :meaning "an online marketplace where people buy and sell new and used goods" :rank 217}
   {:id 218 :word "must" :meaning "a necessary or essential thing" :rank 218}
   {:id 219 :word "store" :meaning "a mercantile establishment for the retail sale of goods or services" :rank 219}
   {:id 220 :word "travel" :meaning "change location" :rank 220}
   {:id 221 :word "comments" :meaning "a statement that expresses a personal opinion or belief or adds information" :rank 221}
   {:id 222 :word "made" :meaning "produced by a manufacturing process" :rank 222}
   {:id 223 :word "development" :meaning "act of improving by expanding or enlarging or refining" :rank 223}
   {:id 224 :word "report" :meaning "to give an account or representation of in words" :rank 224}
   {:id 225 :word "off" :meaning "from a particular thing or place or position (`forth' is obsolete)" :rank 225}
   {:id 226 :word "member" :meaning "one of the persons who compose a social group (especially individuals who have joined and participate in a group organization)" :rank 226}
   {:id 227 :word "details" :meaning "true confidential information" :rank 227}
   {:id 228 :word "line" :meaning "a formation of people or things one beside another" :rank 228}
   {:id 229 :word "terms" :meaning "status with respect to the relations between people or groups" :rank 229}
   {:id 230 :word "before" :meaning "earlier in time" :rank 230}
   {:id 231 :word "hotels" :meaning "a building where travelers can pay for lodging and meals and other services" :rank 231}
   {:id 232 :word "did" :meaning "engage in" :rank 232}
   {:id 233 :word "send" :meaning "cause to go somewhere" :rank 233}
   {:id 234 :word "right" :meaning "an abstract idea of that which is due to a person or governmental body by law or tradition or nature" :rank 234}
   {:id 235 :word "type" :meaning "a subdivision of a particular kind of thing" :rank 235}
   {:id 236 :word "because" :meaning "For the reason (that)." :rank 236}
   {:id 237 :word "local" :meaning "relating to or applicable to or concerned with the administration of a city or town or district rather than a larger area" :rank 237}
   {:id 238 :word "those" :meaning "(demonstrative) The thing, person, idea, quality, event, action or time indicated or understood from context, especially if more remote geographically, temporally or mentally than…" :rank 238}
   {:id 239 :word "using" :meaning "an act that exploits or victimizes someone (treats them unfairly)" :rank 239}
   {:id 240 :word "results" :meaning "a phenomenon that follows and is caused by some previous phenomenon" :rank 240}
   {:id 241 :word "office" :meaning "place of business where professional or clerical duties are performed" :rank 241}
   {:id 242 :word "education" :meaning "the activities of educating or instructing" :rank 242}
   {:id 243 :word "national" :meaning "of or relating to or belonging to a nation or country" :rank 243}
   {:id 244 :word "car" :meaning "a motor vehicle with four wheels" :rank 244}
   {:id 245 :word "design" :meaning "make or work out a plan for" :rank 245}
   {:id 246 :word "take" :meaning "carry out" :rank 246}
   {:id 247 :word "posted" :meaning "affix in a public place or for public notice" :rank 247}
   {:id 248 :word "internet" :meaning "a computer network consisting of a worldwide network of computer networks that use the TCP/IP network protocols to facilitate data transmission and exchange" :rank 248}
   {:id 249 :word "address" :meaning "(computer science) the code that identifies where a piece of information is stored" :rank 249}
   {:id 250 :word "community" :meaning "a group of people living in a particular local area" :rank 250}
   {:id 251 :word "within" :meaning "on the inside" :rank 251}
   {:id 252 :word "states" :meaning "the territory occupied by one of the constituent administrative districts of a nation" :rank 252}
   {:id 253 :word "area" :meaning "a particular geographical region of indefinite boundary (usually serving some special purpose or distinguished by its people or culture or geography)" :rank 253}
   {:id 254 :word "want" :meaning "feel or have a desire for" :rank 254}
   {:id 255 :word "phone" :meaning "electronic equipment that converts sound into electrical signals that can be transmitted over distances and then converts received signals back into sounds" :rank 255}
   {:id 256 :word "dvd" :meaning "a digital recording (as of a movie) on an optical disk that can be played on a computer or a television set" :rank 256}
   {:id 257 :word "shipping" :meaning "the commercial enterprise of moving goods and materials" :rank 257}
   {:id 258 :word "reserved" :meaning "hold back or set aside, especially for future use or contingency" :rank 258}
   {:id 259 :word "subject" :meaning "the subject matter of a conversation or discussion" :rank 259}
   {:id 260 :word "between" :meaning "in the interval" :rank 260}
   {:id 261 :word "forum" :meaning "a public meeting or assembly for open discussion" :rank 261}
   {:id 262 :word "family" :meaning "a social unit living together" :rank 262}
   {:id 263 :word "l" :meaning "the twelfth letter of the English alphabet" :rank 263}
   {:id 264 :word "long" :meaning "primarily temporal sense" :rank 264}
   {:id 265 :word "based" :meaning "having a base" :rank 265}
   {:id 266 :word "w" :meaning "the twenty-third letter of the English alphabet" :rank 266}
   {:id 267 :word "code" :meaning "a set of rules or principles or laws (especially written ones)" :rank 267}
   {:id 268 :word "show" :meaning "give an exhibition of to an interested audience" :rank 268}
   {:id 269 :word "o" :meaning "the fifteenth letter of the English alphabet" :rank 269}
   {:id 270 :word "even" :meaning "used as an intensive especially to indicate something unexpected" :rank 270}
   {:id 271 :word "black" :meaning "being of the achromatic color of maximum darkness" :rank 271}
   {:id 272 :word "check" :meaning "examine so as to determine accuracy, quality, or condition" :rank 272}
   {:id 273 :word "special" :meaning "unique or specific to a person or thing or category" :rank 273}
   {:id 274 :word "prices" :meaning "the property of having material worth (often indicated by the amount of money something would bring if sold)" :rank 274}
   {:id 275 :word "website" :meaning "a computer connected to the internet that maintains a series of web pages on the World Wide Web" :rank 275}
   {:id 276 :word "index" :meaning "a numerical scale used to compare variables with one another or with some reference number" :rank 276}
   {:id 277 :word "being" :meaning "the state or fact of existing" :rank 277}
   {:id 278 :word "women" :meaning "an adult female person (as opposed to a man)" :rank 278}
   {:id 279 :word "much" :meaning "(quantifier used with mass nouns) great in quantity or degree or extent" :rank 279}
   {:id 280 :word "sign" :meaning "a perceptible indication of something not immediately apparent (as a visible clue that something has happened)" :rank 280}
   {:id 281 :word "file" :meaning "record in a public office or in a court of law" :rank 281}
   {:id 282 :word "link" :meaning "make a logical or causal connection" :rank 282}
   {:id 283 :word "open" :meaning "cause to open or to become open" :rank 283}
   {:id 284 :word "today" :meaning "the present time or age" :rank 284}
   {:id 285 :word "technology" :meaning "the practical application of science to commerce or industry" :rank 285}
   {:id 286 :word "south" :meaning "the region of the United States lying to the south of the Mason-Dixon line" :rank 286}
   {:id 287 :word "case" :meaning "an occurrence of something" :rank 287}
   {:id 288 :word "project" :meaning "any piece of work that is undertaken or attempted" :rank 288}
   {:id 289 :word "same" :meaning "same in identity" :rank 289}
   {:id 290 :word "pages" :meaning "one side of one leaf (of a book or magazine or newspaper or letter etc.) or the written or pictorial matter it contains" :rank 290}
   {:id 291 :word "uk" :meaning "a monarchy in northwestern Europe occupying most of the British Isles" :rank 291}
   {:id 292 :word "version" :meaning "an interpretation of a matter from a particular viewpoint" :rank 292}
   {:id 293 :word "section" :meaning "a self-contained part of a larger composition (written or musical)" :rank 293}
   {:id 294 :word "own" :meaning "belonging to or on behalf of a specified person (especially yourself)" :rank 294}
   {:id 295 :word "found" :meaning "set up or found" :rank 295}
   {:id 296 :word "sports" :meaning "an active diversion requiring physical exertion and competition" :rank 296}
   {:id 297 :word "house" :meaning "a dwelling that serves as living quarters for one or more families" :rank 297}
   {:id 298 :word "related" :meaning "being connected either logically or causally or by shared characteristics" :rank 298}
   {:id 299 :word "security" :meaning "the state of being free from danger or injury" :rank 299}
   {:id 300 :word "both" :meaning "(used with count nouns) two considered together" :rank 300}
   {:id 301 :word "g" :meaning "the seventh letter of the English alphabet" :rank 301}
   {:id 302 :word "county" :meaning "(United Kingdom) a region created by territorial division for the purpose of local government" :rank 302}
   {:id 303 :word "american" :meaning "of or relating to the United States of America or its people or language or culture" :rank 303}
   {:id 304 :word "photo" :meaning "a representation of a person or scene in the form of a print or transparent slide" :rank 304}
   {:id 305 :word "game" :meaning "a contest with rules to determine a winner" :rank 305}
   {:id 306 :word "members" :meaning "one of the persons who compose a social group (especially individuals who have joined and participate in a group organization)" :rank 306}
   {:id 307 :word "power" :meaning "possession of controlling influence" :rank 307}
   {:id 308 :word "while" :meaning "a period of indeterminate length (usually short) marked by some action or condition" :rank 308}
   {:id 309 :word "care" :meaning "feel concern or interest" :rank 309}
   {:id 310 :word "network" :meaning "an interconnected system of things or people" :rank 310}
   {:id 311 :word "down" :meaning "spatially or metaphorically from a higher to a lower level or position" :rank 311}
   {:id 312 :word "computer" :meaning "a machine for performing calculations automatically" :rank 312}
   {:id 313 :word "systems" :meaning "instrumentality that combines interrelated interacting artifacts designed to work as a coherent entity" :rank 313}
   {:id 314 :word "three" :meaning "being one more than two" :rank 314}
   {:id 315 :word "total" :meaning "constituting the full quantity or extent" :rank 315}
   {:id 316 :word "place" :meaning "put into a certain place or abstract location" :rank 316}
   {:id 317 :word "end" :meaning "have an end, in a temporal, spatial, or quantitative sense" :rank 317}
   {:id 318 :word "following" :meaning "going or proceeding or coming after in the same direction" :rank 318}
   {:id 319 :word "download" :meaning "transfer a file or program from a central computer to a smaller computer or to a computer at a remote location" :rank 319}
   {:id 320 :word "h" :meaning "the eighth letter of the English alphabet" :rank 320}
   {:id 321 :word "him" :meaning "Honorific alternative letter-case form of him, sometimes used when referring to God or another important figure who is understood from context." :rank 321}
   {:id 322 :word "without" :meaning "Outside, externally. This is still used in the names of some civil parishes in England, e.g. St Cuthbert Without." :rank 322}
   {:id 323 :word "per" :meaning "For each." :rank 323}
   {:id 324 :word "access" :meaning "the right to enter" :rank 324}
   {:id 325 :word "think" :meaning "judge or regard" :rank 325}
   {:id 326 :word "north" :meaning "the region of the United States lying to the north of the Mason-Dixon line" :rank 326}
   {:id 327 :word "resources" :meaning "available source of wealth" :rank 327}
   {:id 328 :word "current" :meaning "occurring in or belonging to the present time" :rank 328}
   {:id 329 :word "posts" :meaning "the position where someone (as a guard or sentry) stands or is assigned to stand" :rank 329}
   {:id 330 :word "big" :meaning "above average in size or number or quantity or magnitude or extent" :rank 330}
   {:id 331 :word "media" :meaning "a means or instrumentality for storing or communicating information" :rank 331}
   {:id 332 :word "law" :meaning "the collection of rules imposed by authority" :rank 332}
   {:id 333 :word "control" :meaning "exercise authoritative control or power over" :rank 333}
   {:id 334 :word "water" :meaning "binary compound that occurs at room temperature as a clear colorless odorless tasteless liquid" :rank 334}
   {:id 335 :word "history" :meaning "the aggregate of past events" :rank 335}
   {:id 336 :word "pictures" :meaning "a visual representation (of an object or scene or person or abstraction) produced on a surface" :rank 336}
   {:id 337 :word "size" :meaning "the physical magnitude of something (how big it is)" :rank 337}
   {:id 338 :word "art" :meaning "the products of human creativity" :rank 338}
   {:id 339 :word "personal" :meaning "concerning or affecting a particular person or his or her private life and personality" :rank 339}
   {:id 340 :word "since" :meaning "From a specified time in the past." :rank 340}
   {:id 341 :word "including" :meaning "have as a part, be made up out of" :rank 341}
   {:id 342 :word "guide" :meaning "direct the course" :rank 342}
   {:id 343 :word "shop" :meaning "a mercantile establishment for the retail sale of goods or services" :rank 343}
   {:id 344 :word "directory" :meaning "an alphabetical list of names and addresses" :rank 344}
   {:id 345 :word "board" :meaning "a committee having supervisory powers" :rank 345}
   {:id 346 :word "location" :meaning "a point or extent in space" :rank 346}
   {:id 347 :word "change" :meaning "cause to change" :rank 347}
   {:id 348 :word "white" :meaning "being of the achromatic color of maximum lightness" :rank 348}
   {:id 349 :word "text" :meaning "the words of something written" :rank 349}
   {:id 350 :word "small" :meaning "limited or below average in number or quantity or magnitude or extent" :rank 350}
   {:id 351 :word "rating" :meaning "an appraisal of the value of something" :rank 351}
   {:id 352 :word "rate" :meaning "a magnitude or frequency relative to a time unit" :rank 352}
   {:id 353 :word "government" :meaning "the organization that is the governing authority of a political unit" :rank 353}
   {:id 354 :word "children" :meaning "a young person of either sex" :rank 354}
   {:id 355 :word "during" :meaning "To last, continue, endure." :rank 355}
   {:id 356 :word "usa" :meaning "North American republic containing 50 states - 48 conterminous states in North America plus Alaska in northwest North America and the Hawaiian Islands in the Pacific Ocean" :rank 356}
   {:id 357 :word "return" :meaning "go or come back to place, condition, or activity where one has been before" :rank 357}
   {:id 358 :word "students" :meaning "a learner who is enrolled in an educational institution" :rank 358}
   {:id 359 :word "v" :meaning "the twenty-second letter of the English alphabet" :rank 359}
   {:id 360 :word "shopping" :meaning "searching for or buying goods or services" :rank 360}
   {:id 361 :word "account" :meaning "a record or narrative description of past events" :rank 361}
   {:id 362 :word "times" :meaning "a more or less definite period of time now or previously present" :rank 362}
   {:id 363 :word "sites" :meaning "the piece of land on which something is located (or is to be located)" :rank 363}
   {:id 364 :word "level" :meaning "a position on a scale of intensity or amount or quality" :rank 364}
   {:id 365 :word "digital" :meaning "displaying numbers rather than scale positions" :rank 365}
   {:id 366 :word "profile" :meaning "an analysis (often in graphical form) representing the extent to which something exhibits various characteristics" :rank 366}
   {:id 367 :word "previous" :meaning "(used especially of persons) of the immediate past" :rank 367}
   {:id 368 :word "form" :meaning "the phonological or orthographic sound or appearance of a word that can be used to describe or identify something" :rank 368}
   {:id 369 :word "events" :meaning "something that happens at a given place and time" :rank 369}
   {:id 370 :word "love" :meaning "have a great affection or liking for" :rank 370}
   {:id 371 :word "old" :meaning "(used especially of persons) having lived for a relatively long time or attained a specific age" :rank 371}
   {:id 372 :word "john" :meaning "a room or building equipped with one or more toilets" :rank 372}
   {:id 373 :word "main" :meaning "most important; the largest or central part of something" :rank 373}
   {:id 374 :word "call" :meaning "assign a specified (usually proper) proper name to" :rank 374}
   {:id 375 :word "hours" :meaning "a period of time assigned for work" :rank 375}
   {:id 376 :word "image" :meaning "an iconic mental representation" :rank 376}
   {:id 377 :word "department" :meaning "a specialized division of a large organization" :rank 377}
   {:id 378 :word "title" :meaning "a heading that names a statute or legislative bill" :rank 378}
   {:id 379 :word "description" :meaning "a statement that represents something in words" :rank 379}
   {:id 380 :word "non" :meaning "negation of a word or group of words" :rank 380}
   {:id 381 :word "k" :meaning "the eleventh letter of the English alphabet" :rank 381}
   {:id 382 :word "y" :meaning "the twenty-fifth letter of the English alphabet" :rank 382}
   {:id 383 :word "insurance" :meaning "promise of reimbursement in the case of loss" :rank 383}
   {:id 384 :word "another" :meaning "any of various alternatives" :rank 384}
   {:id 385 :word "why" :meaning "the cause or intention underlying an action or situation, especially in the phrase `the whys and wherefores'" :rank 385}
   {:id 386 :word "shall" :meaning "(modal, auxiliary verb, defective) Used before a verb to indicate the simple future tense in the first person singular or plural." :rank 386}
   {:id 387 :word "property" :meaning "something owned" :rank 387}
   {:id 388 :word "class" :meaning "a collection of things sharing a common attribute" :rank 388}
   {:id 389 :word "cd" :meaning "a disc used to store music or computer data (compact disc)" :rank 389}
   {:id 390 :word "still" :meaning "with reference to action or condition" :rank 390}
   {:id 391 :word "money" :meaning "the most common medium of exchange" :rank 391}
   {:id 392 :word "quality" :meaning "an essential and distinguishing attribute of something or someone" :rank 392}
   {:id 393 :word "every" :meaning "(used of count nouns) each and all of the members of a group considered singly and without exception" :rank 393}
   {:id 394 :word "listing" :meaning "a database containing an ordered array of items (names or topics)" :rank 394}
   {:id 395 :word "content" :meaning "everything that is included in a collection and that is held or included in something" :rank 395}
   {:id 396 :word "country" :meaning "a politically organized body of people under a single government" :rank 396}
   {:id 397 :word "private" :meaning "confined to particular persons or groups or providing privacy" :rank 397}
   {:id 398 :word "little" :meaning "limited or below average in number or quantity or magnitude or extent" :rank 398}
   {:id 399 :word "visit" :meaning "go to see a place, as for entertainment" :rank 399}
   {:id 400 :word "save" :meaning "save from ruin, destruction, or harm" :rank 400}
   {:id 401 :word "tools" :meaning "an implement used in the practice of a vocation" :rank 401}
   {:id 402 :word "low" :meaning "less than normal in degree or intensity or amount" :rank 402}
   {:id 403 :word "reply" :meaning "react verbally" :rank 403}
   {:id 404 :word "customer" :meaning "someone who pays for goods or services" :rank 404}
   {:id 405 :word "december" :meaning "the last (12th) month of the year" :rank 405}
   {:id 406 :word "compare" :meaning "examine and note the similarities or differences of" :rank 406}
   {:id 407 :word "movies" :meaning "a form of entertainment that enacts a story by sound and a sequence of images giving the illusion of continuous movement" :rank 407}
   {:id 408 :word "include" :meaning "have as a part, be made up out of" :rank 408}
   {:id 409 :word "college" :meaning "the body of faculty and students of a college" :rank 409}
   {:id 410 :word "value" :meaning "a numerical quantity measured or assigned or computed" :rank 410}
   {:id 411 :word "article" :meaning "nonfictional prose forming an independent part of a publication" :rank 411}
   {:id 412 :word "york" :meaning "the English royal house (a branch of the Plantagenet line) that reigned from 1461 to 1485" :rank 412}
   {:id 413 :word "man" :meaning "an adult person who is male (as opposed to a woman)" :rank 413}
   {:id 414 :word "card" :meaning "one of a set of small pieces of stiff paper marked in various ways and used for playing games or for telling fortunes" :rank 414}
   {:id 415 :word "jobs" :meaning "the principal activity in your life that you do to earn money" :rank 415}
   {:id 416 :word "provide" :meaning "give something useful or necessary to" :rank 416}
   {:id 417 :word "j" :meaning "the tenth letter of the English alphabet" :rank 417}
   {:id 418 :word "food" :meaning "any substance that can be metabolized by an animal to give energy and build tissue" :rank 418}
   {:id 419 :word "source" :meaning "the place where something begins, where it springs into being" :rank 419}
   {:id 420 :word "author" :meaning "writes (books or stories or articles or the like) professionally (for pay)" :rank 420}
   {:id 421 :word "different" :meaning "unlike in nature or quality or form or degree" :rank 421}
   {:id 422 :word "press" :meaning "exert pressure or force to or upon" :rank 422}
   {:id 423 :word "u" :meaning "the twenty-first letter of the English alphabet" :rank 423}
   {:id 424 :word "learn" :meaning "gain knowledge or skills" :rank 424}
   {:id 425 :word "sale" :meaning "a particular instance of selling" :rank 425}
   {:id 426 :word "around" :meaning "in the area or vicinity" :rank 426}
   {:id 427 :word "print" :meaning "put into print" :rank 427}
   {:id 428 :word "course" :meaning "education imparted in a series of lessons or meetings" :rank 428}
   {:id 429 :word "job" :meaning "the principal activity in your life that you do to earn money" :rank 429}
   {:id 430 :word "canada" :meaning "a nation in northern North America" :rank 430}
   {:id 431 :word "process" :meaning "a particular course of action intended to achieve a result" :rank 431}
   {:id 432 :word "teen" :meaning "being of the age 13 through 19" :rank 432}
   {:id 433 :word "room" :meaning "an area within a building enclosed by walls and floor and ceiling" :rank 433}
   {:id 434 :word "stock" :meaning "the capital raised by a corporation through the issue of shares entitling holders to an ownership interest (equity)" :rank 434}
   {:id 435 :word "training" :meaning "activity leading to skilled behavior" :rank 435}
   {:id 436 :word "too" :meaning "to a degree exceeding normal or proper limits" :rank 436}
   {:id 437 :word "credit" :meaning "approval" :rank 437}
   {:id 438 :word "point" :meaning "a particular place, moment, or idea; a sharp end" :rank 438}
   {:id 439 :word "join" :meaning "become part of" :rank 439}
   {:id 440 :word "science" :meaning "a particular branch of scientific knowledge" :rank 440}
   {:id 441 :word "men" :meaning "the force of workers available" :rank 441}
   {:id 442 :word "categories" :meaning "a collection of things sharing a common attribute" :rank 442}
   {:id 443 :word "advanced" :meaning "farther along in physical or mental development" :rank 443}
   {:id 444 :word "west" :meaning "the countries of (originally) Europe and (now including) North America and South America" :rank 444}
   {:id 445 :word "sales" :meaning "income (at invoice values) received for goods and services over some given period of time" :rank 445}
   {:id 446 :word "look" :meaning "perceive with attention" :rank 446}
   {:id 447 :word "english" :meaning "of or relating to or characteristic of England or its culture or people" :rank 447}
   {:id 448 :word "left" :meaning "being or located on or directed toward the side of the body to the west when facing north" :rank 448}
   {:id 449 :word "team" :meaning "a cooperative unit (especially in sports)" :rank 449}
   {:id 450 :word "estate" :meaning "everything you own" :rank 450}
   {:id 451 :word "box" :meaning "a (usually rectangular) container" :rank 451}
   {:id 452 :word "conditions" :meaning "the prevailing context that influences the performance or the outcome of a process" :rank 452}
   {:id 453 :word "select" :meaning "pick out, select, or choose from a number of alternatives" :rank 453}
   {:id 454 :word "windows" :meaning "(trademark) an operating system with a graphical user interface" :rank 454}
   {:id 455 :word "photos" :meaning "a representation of a person or scene in the form of a print or transparent slide" :rank 455}
   {:id 456 :word "gay" :meaning "bright and pleasant" :rank 456}
   {:id 457 :word "thread" :meaning "a fine cord of twisted fibers (of cotton or silk or wool or nylon etc.) used in sewing and weaving" :rank 457}
   {:id 458 :word "week" :meaning "any period of seven consecutive days" :rank 458}
   {:id 459 :word "category" :meaning "a collection of things sharing a common attribute" :rank 459}
   {:id 460 :word "note" :meaning "make mention of" :rank 460}
   {:id 461 :word "live" :meaning "inhabit or live in" :rank 461}
   {:id 462 :word "large" :meaning "above average in size or number or quantity or magnitude or extent" :rank 462}
   {:id 463 :word "gallery" :meaning "spectators at a golf or tennis match" :rank 463}
   {:id 464 :word "table" :meaning "a set of data arranged in rows and columns" :rank 464}
   {:id 465 :word "register" :meaning "record in writing" :rank 465}
   {:id 466 :word "however" :meaning "despite anything to the contrary (usually following a concession)" :rank 466}
   {:id 467 :word "june" :meaning "the month following May and preceding July" :rank 467}
   {:id 468 :word "october" :meaning "the month following September and preceding November" :rank 468}
   {:id 469 :word "november" :meaning "the month following October and preceding December" :rank 469}
   {:id 470 :word "market" :meaning "the world of commercial activity where goods and services are bought and sold" :rank 470}
   {:id 471 :word "library" :meaning "a room where books are kept" :rank 471}
   {:id 472 :word "really" :meaning "in accordance with truth or fact or reality" :rank 472}
   {:id 473 :word "action" :meaning "something done (usually as opposed to something said)" :rank 473}
   {:id 474 :word "start" :meaning "take the first step or steps in carrying out an action" :rank 474}
   {:id 475 :word "series" :meaning "similar things placed in order or happening one after another" :rank 475}
   {:id 476 :word "model" :meaning "a hypothetical description of a complex entity or process" :rank 476}
   {:id 477 :word "features" :meaning "a prominent attribute or aspect of something" :rank 477}
   {:id 478 :word "air" :meaning "a mixture of gases (especially oxygen) required for breathing" :rank 478}
   {:id 479 :word "industry" :meaning "the people or companies engaged in a particular kind of commercial enterprise" :rank 479}
   {:id 480 :word "plan" :meaning "a series of steps to be carried out or goals to be accomplished" :rank 480}
   {:id 481 :word "human" :meaning "characteristic of humanity" :rank 481}
   {:id 482 :word "provided" :meaning "give something useful or necessary to" :rank 482}
   {:id 483 :word "tv" :meaning "short for television" :rank 483}
   {:id 484 :word "yes" :meaning "an affirmative" :rank 484}
   {:id 485 :word "required" :meaning "necessary for relief or supply" :rank 485}
   {:id 486 :word "second" :meaning "coming next after the first in position in space or time or degree or magnitude" :rank 486}
   {:id 487 :word "hot" :meaning "used of physical heat" :rank 487}
   {:id 488 :word "accessories" :meaning "clothing that is worn or carried, but not part of your main clothing" :rank 488}
   {:id 489 :word "cost" :meaning "the total spent for goods or services including money and time and labor" :rank 489}
   {:id 490 :word "movie" :meaning "a form of entertainment that enacts a story by sound and a sequence of images giving the illusion of continuous movement" :rank 490}
   {:id 491 :word "forums" :meaning "a public meeting or assembly for open discussion" :rank 491}
   {:id 492 :word "march" :meaning "march in a procession" :rank 492}
   {:id 493 :word "la" :meaning "a musical note; the sixth note of a scale" :rank 493}
   {:id 494 :word "september" :meaning "the month following August and preceding October" :rank 494}
   {:id 495 :word "better" :meaning "(comparative of `good') superior to another (of the same class or set or kind) in excellence or quality or desirability or suitability" :rank 495}
   {:id 496 :word "say" :meaning "express in words" :rank 496}
   {:id 497 :word "questions" :meaning "an instance of questioning" :rank 497}
   {:id 498 :word "july" :meaning "the month following June and preceding August" :rank 498}
   {:id 499 :word "yahoo" :meaning "a person who is not very intelligent or interested in culture" :rank 499}
   {:id 500 :word "going" :meaning "the act of departing" :rank 500}
   {:id 501 :word "medical" :meaning "relating to the study or practice of medicine" :rank 501}
   {:id 502 :word "test" :meaning "put to the test, as for its quality, or give experimental use to" :rank 502}
   {:id 503 :word "friend" :meaning "a person you know well and regard with affection and trust" :rank 503}
   {:id 504 :word "come" :meaning "move toward, travel toward something or somebody or approach something or somebody" :rank 504}
   {:id 505 :word "dec" :meaning "the last (12th) month of the year" :rank 505}
   {:id 506 :word "server" :meaning "a person whose occupation is to serve at table (as in a restaurant)" :rank 506}
   {:id 507 :word "pc" :meaning "a small digital computer based on a microprocessor and designed to be used by one person at a time" :rank 507}
   {:id 508 :word "study" :meaning "a detailed critical inspection" :rank 508}
   {:id 509 :word "application" :meaning "the act of bringing something to bear" :rank 509}
   {:id 510 :word "cart" :meaning "a heavy open wagon usually having two wheels and drawn by an animal" :rank 510}
   {:id 511 :word "staff" :meaning "personnel who assist their superior in carrying out an assigned task" :rank 511}
   {:id 512 :word "articles" :meaning "nonfictional prose forming an independent part of a publication" :rank 512}
   {:id 513 :word "san" :meaning "A letter of the Archaic Greek alphabet (uppercase Ϻ, lowercase ϻ) that came after pi and before qoppa." :rank 513}
   {:id 514 :word "feedback" :meaning "the process in which part of the output of a system is returned to its input in order to regulate its further output" :rank 514}
   {:id 515 :word "again" :meaning "anew" :rank 515}
   {:id 516 :word "play" :meaning "participate in games or sport" :rank 516}
   {:id 517 :word "looking" :meaning "appearing to be as specified" :rank 517}
   {:id 518 :word "issues" :meaning "an important question that is in dispute and must be settled" :rank 518}
   {:id 519 :word "april" :meaning "the month following March and preceding May" :rank 519}
   {:id 520 :word "never" :meaning "not ever" :rank 520}
   {:id 521 :word "users" :meaning "a person who makes use of a thing" :rank 521}
   {:id 522 :word "complete" :meaning "having every necessary or normal part or component or step" :rank 522}
   {:id 523 :word "street" :meaning "a thoroughfare (usually including sidewalks) that is lined with buildings" :rank 523}
   {:id 524 :word "topic" :meaning "the subject matter of a conversation or discussion" :rank 524}
   {:id 525 :word "comment" :meaning "a statement that expresses a personal opinion or belief or adds information" :rank 525}
   {:id 526 :word "financial" :meaning "involving financial matters" :rank 526}
   {:id 527 :word "things" :meaning "any movable possession (especially articles of clothing)" :rank 527}
   {:id 528 :word "working" :meaning "actively engaged in paid work" :rank 528}
   {:id 529 :word "against" :meaning "In a contrary direction to." :rank 529}
   {:id 530 :word "standard" :meaning "conforming to or constituting a standard of measurement or value" :rank 530}
   {:id 531 :word "tax" :meaning "charge against a citizen's person or property or activity for the support of government" :rank 531}
   {:id 532 :word "person" :meaning "a human being" :rank 532}
   {:id 533 :word "below" :meaning "in or to a place that is lower" :rank 533}
   {:id 534 :word "mobile" :meaning "migratory" :rank 534}
   {:id 535 :word "less" :meaning "used to form the comparative of some adjectives and adverbs" :rank 535}
   {:id 536 :word "got" :meaning "come into the possession of something concrete or abstract" :rank 536}
   {:id 537 :word "blog" :meaning "a shared on-line journal where people can post diary entries about their personal experiences and hobbies" :rank 537}
   {:id 538 :word "party" :meaning "an organization to gain political power" :rank 538}
   {:id 539 :word "payment" :meaning "a sum of money paid or a claim discharged" :rank 539}
   {:id 540 :word "equipment" :meaning "an instrumentality needed for an undertaking or to perform a service" :rank 540}
   {:id 541 :word "login" :meaning "A combination of a user's identification and password used to enter a computer, program, network, etc." :rank 541}
   {:id 542 :word "student" :meaning "a learner who is enrolled in an educational institution" :rank 542}
   {:id 543 :word "let" :meaning "make it possible through a specific action or lack of action for something to happen" :rank 543}
   {:id 544 :word "programs" :meaning "a series of steps to be carried out or goals to be accomplished" :rank 544}
   {:id 545 :word "offers" :meaning "the verbal act of offering" :rank 545}
   {:id 546 :word "legal" :meaning "established by or founded upon law or official or accepted rules" :rank 546}
   {:id 547 :word "above" :meaning "at an earlier place" :rank 547}
   {:id 548 :word "recent" :meaning "new" :rank 548}
   {:id 549 :word "park" :meaning "a large area of land preserved in its natural state as public property" :rank 549}
   {:id 550 :word "stores" :meaning "a mercantile establishment for the retail sale of goods or services" :rank 550}
   {:id 551 :word "side" :meaning "a place within a region identified relative to a center or reference location" :rank 551}
   {:id 552 :word "act" :meaning "a legal document codifying the result of deliberations of a committee or society or legislative body" :rank 552}
   {:id 553 :word "problem" :meaning "a state of difficulty that needs to be resolved" :rank 553}
   {:id 554 :word "red" :meaning "of a color at the end of the color spectrum (next to orange)" :rank 554}
   {:id 555 :word "give" :meaning "cause to have, in the abstract sense or physical sense" :rank 555}
   {:id 556 :word "memory" :meaning "something that is remembered" :rank 556}
   {:id 557 :word "performance" :meaning "a dramatic or musical entertainment" :rank 557}
   {:id 558 :word "social" :meaning "relating to human society and its members" :rank 558}
   {:id 559 :word "q" :meaning "the seventeenth letter of the English alphabet" :rank 559}
   {:id 560 :word "august" :meaning "the month following July and preceding September" :rank 560}
   {:id 561 :word "quote" :meaning "repeat a passage from" :rank 561}
   {:id 562 :word "language" :meaning "a systematic means of communicating by the use of sounds or conventional symbols" :rank 562}
   {:id 563 :word "story" :meaning "a message that tells the particulars of an act or occurrence or course of events" :rank 563}
   {:id 564 :word "sell" :meaning "exchange or deliver for money or its equivalent" :rank 564}
   {:id 565 :word "options" :meaning "the right to buy or sell property at an agreed price" :rank 565}
   {:id 566 :word "experience" :meaning "the accumulation of knowledge or skill that results from direct participation in events or activities" :rank 566}
   {:id 567 :word "rates" :meaning "a local tax on property (usually used in the plural)" :rank 567}
   {:id 568 :word "create" :meaning "make or cause to be or to become" :rank 568}
   {:id 569 :word "key" :meaning "metal device shaped in such a way that when it is inserted into the appropriate lock the lock's mechanism can be rotated" :rank 569}
   {:id 570 :word "body" :meaning "the entire structure of an organism (an animal, plant, or human being)" :rank 570}
   {:id 571 :word "young" :meaning "(used of living things especially persons) in an early period of life or development or growth" :rank 571}
   {:id 572 :word "america" :meaning "North American republic containing 50 states - 48 conterminous states in North America plus Alaska in northwest North America and the Hawaiian Islands in the Pacific Ocean" :rank 572}
   {:id 573 :word "important" :meaning "of great significance or value" :rank 573}
   {:id 574 :word "field" :meaning "a piece of land cleared of trees and usually enclosed" :rank 574}
   {:id 575 :word "few" :meaning "a quantifier that can be used with count nouns and is often preceded by `a'" :rank 575}
   {:id 576 :word "east" :meaning "situated in or facing or moving toward the east" :rank 576}
   {:id 577 :word "paper" :meaning "a material made of cellulose pulp derived mainly from wood or rags or certain grasses" :rank 577}
   {:id 578 :word "single" :meaning "being or characteristic of a single thing or person" :rank 578}
   {:id 579 :word "ii" :meaning "the Roman numeral for two" :rank 579}
   {:id 580 :word "age" :meaning "how long something has existed" :rank 580}
   {:id 581 :word "activities" :meaning "any specific behavior" :rank 581}
   {:id 582 :word "club" :meaning "a team of professional baseball players who play and travel together" :rank 582}
   {:id 583 :word "example" :meaning "an item of information that is typical of a class or group" :rank 583}
   {:id 584 :word "girls" :meaning "a young woman" :rank 584}
   {:id 585 :word "additional" :meaning "further or added" :rank 585}
   {:id 586 :word "password" :meaning "a secret word or phrase known only to a restricted group" :rank 586}
   {:id 587 :word "z" :meaning "the twenty-sixth and last letter of the English alphabet" :rank 587}
   {:id 588 :word "latest" :meaning "up to the immediate present" :rank 588}
   {:id 589 :word "something" :meaning "An object whose nature is yet to be defined." :rank 589}
   {:id 590 :word "road" :meaning "an open way (generally public) for travel or transportation" :rank 590}
   {:id 591 :word "gift" :meaning "something acquired without compensation" :rank 591}
   {:id 592 :word "question" :meaning "an instance of questioning" :rank 592}
   {:id 593 :word "changes" :meaning "an event that occurs when something passes from one state or phase to another" :rank 593}
   {:id 594 :word "night" :meaning "the time after sunset and before sunrise while it is dark outside" :rank 594}
   {:id 595 :word "ca" :meaning "short for California, a state in the western United States" :rank 595}
   {:id 596 :word "hard" :meaning "not easy" :rank 596}
   {:id 597 :word "texas" :meaning "the second largest state" :rank 597}
   {:id 598 :word "oct" :meaning "the month following September and preceding November" :rank 598}
   {:id 599 :word "pay" :meaning "give money, usually in exchange for goods or services" :rank 599}
   {:id 600 :word "four" :meaning "being one more than three" :rank 600}
   {:id 601 :word "poker" :meaning "fire iron consisting of a metal rod with a handle" :rank 601}
   {:id 602 :word "status" :meaning "the relative position or standing of things or especially persons in a society" :rank 602}
   {:id 603 :word "browse" :meaning "vegetation (such as young shoots, twigs, and leaves) that is suitable for animals to eat" :rank 603}
   {:id 604 :word "issue" :meaning "an important question that is in dispute and must be settled" :rank 604}
   {:id 605 :word "range" :meaning "an area in which something acts or operates or has power or control:" :rank 605}
   {:id 606 :word "building" :meaning "a structure that has a roof and walls and stands more or less permanently in one place" :rank 606}
   {:id 607 :word "seller" :meaning "someone who promotes or exchanges goods or services for money" :rank 607}
   {:id 608 :word "court" :meaning "an assembly (including one or more judges) to conduct judicial business" :rank 608}
   {:id 609 :word "february" :meaning "the month following January and preceding March" :rank 609}
   {:id 610 :word "always" :meaning "at all times" :rank 610}
   {:id 611 :word "result" :meaning "a phenomenon that follows and is caused by some previous phenomenon" :rank 611}
   {:id 612 :word "audio" :meaning "the audible part of a transmitted signal" :rank 612}
   {:id 613 :word "light" :meaning "(physics) electromagnetic radiation that can produce a visual sensation" :rank 613}
   {:id 614 :word "write" :meaning "produce a literary work" :rank 614}
   {:id 615 :word "war" :meaning "the waging of armed conflict against an enemy" :rank 615}
   {:id 616 :word "nov" :meaning "the month following October and preceding December" :rank 616}
   {:id 617 :word "offer" :meaning "make available or accessible, provide or furnish" :rank 617}
   {:id 618 :word "blue" :meaning "of the color intermediate between green and violet" :rank 618}
   {:id 619 :word "groups" :meaning "any number of entities (members) considered as a unit" :rank 619}
   {:id 620 :word "al" :meaning "the chemical symbol for aluminium (aluminum)" :rank 620}
   {:id 621 :word "easy" :meaning "posing no difficulty" :rank 621}
   {:id 622 :word "given" :meaning "acknowledged as a supposition" :rank 622}
   {:id 623 :word "files" :meaning "a set of related records (either written or electronic) kept together" :rank 623}
   {:id 624 :word "event" :meaning "something that happens at a given place and time" :rank 624}
   {:id 625 :word "release" :meaning "release, as from one's grip" :rank 625}
   {:id 626 :word "analysis" :meaning "an investigation of the component parts of a whole and their relations in making up the whole" :rank 626}
   {:id 627 :word "request" :meaning "express the need or desire for" :rank 627}
   {:id 628 :word "fax" :meaning "duplicator that transmits the copy by wire or radio" :rank 628}
   {:id 629 :word "china" :meaning "a communist nation that covers a vast territory in eastern Asia" :rank 629}
   {:id 630 :word "making" :meaning "the act that results in something coming to be" :rank 630}
   {:id 631 :word "picture" :meaning "a visual representation (of an object or scene or person or abstraction) produced on a surface" :rank 631}
   {:id 632 :word "needs" :meaning "a condition requiring relief" :rank 632}
   {:id 633 :word "possible" :meaning "capable of happening or existing" :rank 633}
   {:id 634 :word "might" :meaning "physical strength" :rank 634}
   {:id 635 :word "professional" :meaning "engaged in a profession or engaging in as a profession or means of livelihood" :rank 635}
   {:id 636 :word "yet" :meaning "up to the present time" :rank 636}
   {:id 637 :word "month" :meaning "one of the twelve divisions of the calendar year" :rank 637}
   {:id 638 :word "major" :meaning "of greater importance or stature or rank" :rank 638}
   {:id 639 :word "star" :meaning "(astronomy) a celestial body of hot gases that radiates energy derived from thermonuclear reactions in the interior" :rank 639}
   {:id 640 :word "areas" :meaning "a particular geographical region of indefinite boundary (usually serving some special purpose or distinguished by its people or culture or geography)" :rank 640}
   {:id 641 :word "future" :meaning "the time yet to come" :rank 641}
   {:id 642 :word "space" :meaning "the unlimited expanse in which everything is located" :rank 642}
   {:id 643 :word "committee" :meaning "a special group delegated to consider some matter" :rank 643}
   {:id 644 :word "hand" :meaning "the (prehensile) extremity of the superior limb" :rank 644}
   {:id 645 :word "sun" :meaning "the star that is the source of light and heat for the planets in the solar system" :rank 645}
   {:id 646 :word "cards" :meaning "a game played with playing cards" :rank 646}
   {:id 647 :word "problems" :meaning "a state of difficulty that needs to be resolved" :rank 647}
   {:id 648 :word "london" :meaning "the capital and largest city of England" :rank 648}
   {:id 649 :word "washington" :meaning "the capital of the United States in the District of Columbia and a tourist mecca" :rank 649}
   {:id 650 :word "meeting" :meaning "a formally arranged gathering" :rank 650}
   {:id 651 :word "rss" :meaning "a web feed format that lets readers follow updates to a website" :rank 651}
   {:id 652 :word "become" :meaning "enter or assume a certain state or condition" :rank 652}
   {:id 653 :word "interest" :meaning "a sense of concern with and curiosity about someone or something" :rank 653}
   {:id 654 :word "id" :meaning "short for identification; a name or number that identifies a person or thing" :rank 654}
   {:id 655 :word "child" :meaning "a young person of either sex" :rank 655}
   {:id 656 :word "keep" :meaning "keep in a certain state, position, or activity" :rank 656}
   {:id 657 :word "enter" :meaning "to come or go into" :rank 657}
   {:id 658 :word "california" :meaning "a state in the western United States on the Pacific" :rank 658}
   {:id 659 :word "porn" :meaning "creative activity (writing or pictures or films etc.) of no literary or artistic value other than to stimulate sexual desire" :rank 659}
   {:id 660 :word "share" :meaning "assets belonging to or due to or contributed by an individual person or group" :rank 660}
   {:id 661 :word "similar" :meaning "marked by correspondence or resemblance" :rank 661}
   {:id 662 :word "garden" :meaning "a plot of ground where plants are cultivated" :rank 662}
   {:id 663 :word "schools" :meaning "an educational institution" :rank 663}
   {:id 664 :word "million" :meaning "(in Roman numerals, M written with a macron over it) denoting a quantity consisting of 1,000,000 items or units" :rank 664}
   {:id 665 :word "added" :meaning "make an addition (to)" :rank 665}
   {:id 666 :word "reference" :meaning "a remark that calls attention to something or someone" :rank 666}
   {:id 667 :word "companies" :meaning "an institution created to conduct business" :rank 667}
   {:id 668 :word "listed" :meaning "on a list" :rank 668}
   {:id 669 :word "baby" :meaning "a very young child (birth to 1 year) who has not yet begun to walk or talk" :rank 669}
   {:id 670 :word "learning" :meaning "the cognitive process of acquiring skill or knowledge" :rank 670}
   {:id 671 :word "energy" :meaning "(physics) a thermodynamic quantity equivalent to the capacity of a physical system to do work" :rank 671}
   {:id 672 :word "run" :meaning "move fast by using one's feet, with one foot off the ground at any given time" :rank 672}
   {:id 673 :word "delivery" :meaning "the act of delivering or distributing something (as goods or mail)" :rank 673}
   {:id 674 :word "net" :meaning "remaining after all deductions" :rank 674}
   {:id 675 :word "popular" :meaning "regarded with great favor, approval, or affection especially by the general public" :rank 675}
   {:id 676 :word "term" :meaning "a word or expression used for some particular thing" :rank 676}
   {:id 677 :word "film" :meaning "a form of entertainment that enacts a story by sound and a sequence of images giving the illusion of continuous movement" :rank 677}
   {:id 678 :word "stories" :meaning "a message that tells the particulars of an act or occurrence or course of events" :rank 678}
   {:id 679 :word "put" :meaning "put into a certain place or abstract location" :rank 679}
   {:id 680 :word "computers" :meaning "a machine for performing calculations automatically" :rank 680}
   {:id 681 :word "journal" :meaning "a daily written record of (usually personal) experiences and observations" :rank 681}
   {:id 682 :word "reports" :meaning "a written document describing the findings of some individual or group" :rank 682}
   {:id 683 :word "co" :meaning "short for company; used in business names" :rank 683}
   {:id 684 :word "try" :meaning "make an effort or attempt" :rank 684}
   {:id 685 :word "welcome" :meaning "accept gladly" :rank 685}
   {:id 686 :word "central" :meaning "serving as an essential component" :rank 686}
   {:id 687 :word "images" :meaning "an iconic mental representation" :rank 687}
   {:id 688 :word "president" :meaning "an executive officer of a firm or corporation" :rank 688}
   {:id 689 :word "notice" :meaning "discover or determine the existence, presence, or fact of" :rank 689}
   {:id 690 :word "god" :meaning "the supernatural being conceived as the perfect and omnipotent and omniscient originator and ruler of the universe" :rank 690}
   {:id 691 :word "original" :meaning "preceding all others in time or being as first made or performed" :rank 691}
   {:id 692 :word "head" :meaning "the upper part of the human body or the front part of the body in animals" :rank 692}
   {:id 693 :word "radio" :meaning "medium for communication" :rank 693}
   {:id 694 :word "until" :meaning "Up to the time of (something happening)." :rank 694}
   {:id 695 :word "cell" :meaning "any small compartment" :rank 695}
   {:id 696 :word "color" :meaning "a visual attribute of things that results from the light they emit or transmit or reflect" :rank 696}
   {:id 697 :word "self" :meaning "your consciousness of your own identity" :rank 697}
   {:id 698 :word "council" :meaning "a body serving in an administrative capacity" :rank 698}
   {:id 699 :word "away" :meaning "from a particular thing or place or position (`forth' is obsolete)" :rank 699}
   {:id 700 :word "includes" :meaning "have as a part, be made up out of" :rank 700}
   {:id 701 :word "track" :meaning "a line or route along which something travels or moves" :rank 701}
   {:id 702 :word "australia" :meaning "a nation occupying the whole of the Australian continent" :rank 702}
   {:id 703 :word "discussion" :meaning "an extended communication (often interactive) dealing with some particular topic" :rank 703}
   {:id 704 :word "archive" :meaning "a depository containing historical records and documents" :rank 704}
   {:id 705 :word "once" :meaning "on one occasion" :rank 705}
   {:id 706 :word "others" :meaning "An other, another (person, etc), more often rendered as another." :rank 706}
   {:id 707 :word "entertainment" :meaning "an activity that is diverting and that holds the attention" :rank 707}
   {:id 708 :word "agreement" :meaning "the statement (oral or written) of an exchange of promises" :rank 708}
   {:id 709 :word "format" :meaning "the organization of information according to preset specifications (usually for computer processing)" :rank 709}
   {:id 710 :word "least" :meaning "used to form the superlative" :rank 710}
   {:id 711 :word "society" :meaning "an extended social group having a distinctive cultural and economic organization" :rank 711}
   {:id 712 :word "months" :meaning "one of the twelve divisions of the calendar year" :rank 712}
   {:id 713 :word "log" :meaning "a segment of the trunk of a tree when stripped of branches" :rank 713}
   {:id 714 :word "safety" :meaning "the state of being certain that adverse effects will not be caused by some agent under defined conditions" :rank 714}
   {:id 715 :word "friends" :meaning "a person you know well and regard with affection and trust" :rank 715}
   {:id 716 :word "sure" :meaning "having or feeling no doubt or uncertainty" :rank 716}
   {:id 717 :word "faq" :meaning "a list of questions that are frequently asked (about a given topic) along with their answers" :rank 717}
   {:id 718 :word "trade" :meaning "the commercial exchange (buying and selling on domestic or international markets) of goods and services" :rank 718}
   {:id 719 :word "edition" :meaning "the form in which a text (especially a printed book) is published" :rank 719}
   {:id 720 :word "cars" :meaning "a motor vehicle with four wheels" :rank 720}
   {:id 721 :word "messages" :meaning "a communication (usually brief) that is written or spoken or signaled" :rank 721}
   {:id 722 :word "marketing" :meaning "the exchange of goods for an agreed sum of money" :rank 722}
   {:id 723 :word "tell" :meaning "express in words" :rank 723}
   {:id 724 :word "further" :meaning "to or at a greater extent or degree or a more advanced stage (`further' is used more often than `farther' in this abstract sense)" :rank 724}
   {:id 725 :word "updated" :meaning "modernize or bring up to date" :rank 725}
   {:id 726 :word "association" :meaning "a formal organization of people or groups of people" :rank 726}
   {:id 727 :word "able" :meaning "(usually followed by `to') having the necessary means or skill or know-how or authority to do something" :rank 727}
   {:id 728 :word "having" :meaning "have or possess, either in a concrete or an abstract sense" :rank 728}
   {:id 729 :word "provides" :meaning "give something useful or necessary to" :rank 729}
   {:id 730 :word "david" :meaning "patron saint of Wales (circa 520-600)" :rank 730}
   {:id 731 :word "fun" :meaning "activities that are enjoyable or amusing" :rank 731}
   {:id 732 :word "already" :meaning "prior to a specified or implied time" :rank 732}
   {:id 733 :word "green" :meaning "of the color between blue and yellow in the color spectrum" :rank 733}
   {:id 734 :word "studies" :meaning "a detailed critical inspection" :rank 734}
   {:id 735 :word "close" :meaning "near in space or time; to shut something" :rank 735}
   {:id 736 :word "common" :meaning "belonging to or participated in by a community as a whole" :rank 736}
   {:id 737 :word "drive" :meaning "operate or control a vehicle" :rank 737}
   {:id 738 :word "specific" :meaning "(sometimes followed by `to') applying to or characterized by or distinguishing something particular or special or unique" :rank 738}
   {:id 739 :word "several" :meaning "considered individually" :rank 739}
   {:id 740 :word "gold" :meaning "coins made of gold" :rank 740}
   {:id 741 :word "feb" :meaning "the month following January and preceding March" :rank 741}
   {:id 742 :word "living" :meaning "the experience of being alive" :rank 742}
   {:id 743 :word "sep" :meaning "the month following August and preceding October" :rank 743}
   {:id 744 :word "collection" :meaning "several things grouped together or considered as a whole" :rank 744}
   {:id 745 :word "called" :meaning "assign a specified (usually proper) proper name to" :rank 745}
   {:id 746 :word "short" :meaning "primarily temporal sense" :rank 746}
   {:id 747 :word "arts" :meaning "studies intended to provide general knowledge and intellectual skills (rather than occupational or professional skills)" :rank 747}
   {:id 748 :word "lot" :meaning "(often followed by `of') a large number or amount or extent" :rank 748}
   {:id 749 :word "ask" :meaning "inquire about" :rank 749}
   {:id 750 :word "display" :meaning "to show, make visible or apparent" :rank 750}
   {:id 751 :word "limited" :meaning "small in range or scope" :rank 751}
   {:id 752 :word "powered" :meaning "supply the force or power for the functioning of" :rank 752}
   {:id 753 :word "solutions" :meaning "a homogeneous mixture of two or more substances" :rank 753}
   {:id 754 :word "means" :meaning "how a result is obtained or an end is achieved" :rank 754}
   {:id 755 :word "director" :meaning "someone who controls resources and expenditures" :rank 755}
   {:id 756 :word "daily" :meaning "every day" :rank 756}
   {:id 757 :word "beach" :meaning "an area of sand sloping down to the water of a sea or lake" :rank 757}
   {:id 758 :word "past" :meaning "the time that has elapsed" :rank 758}
   {:id 759 :word "natural" :meaning "in accordance with nature" :rank 759}
   {:id 760 :word "whether" :meaning "Which of two." :rank 760}
   {:id 761 :word "due" :meaning "owed and payable immediately or on demand" :rank 761}
   {:id 762 :word "et" :meaning "Latin for and, used in the phrase et cetera (and so on)" :rank 762}
   {:id 763 :word "electronics" :meaning "the branch of physics that deals with the emission and effects of electrons and with the use of electronic devices" :rank 763}
   {:id 764 :word "five" :meaning "being one more than four" :rank 764}
   {:id 765 :word "upon" :meaning "Being the target of an action." :rank 765}
   {:id 766 :word "period" :meaning "an amount of time" :rank 766}
   {:id 767 :word "planning" :meaning "an act of formulating a program for a definite course of action" :rank 767}
   {:id 768 :word "database" :meaning "an organized body of related information" :rank 768}
   {:id 769 :word "says" :meaning "the chance to speak" :rank 769}
   {:id 770 :word "official" :meaning "a worker who holds or is invested with an office" :rank 770}
   {:id 771 :word "weather" :meaning "the atmospheric conditions that comprise the state of the atmosphere in terms of temperature and wind and clouds and precipitation" :rank 771}
   {:id 772 :word "mar" :meaning "make imperfect" :rank 772}
   {:id 773 :word "land" :meaning "the land on which real estate is located" :rank 773}
   {:id 774 :word "average" :meaning "approximating the statistical norm or average or expected value" :rank 774}
   {:id 775 :word "done" :meaning "having finished or arrived at completion" :rank 775}
   {:id 776 :word "technical" :meaning "of or relating to technique or proficiency in a practical skill" :rank 776}
   {:id 777 :word "window" :meaning "a framework of wood or metal that contains a glass windowpane and is built into a wall or roof to admit light or air" :rank 777}
   {:id 778 :word "france" :meaning "a republic in western Europe" :rank 778}
   {:id 779 :word "pro" :meaning "in favor of (an action or proposal etc.)" :rank 779}
   {:id 780 :word "region" :meaning "the extended spatial location of something" :rank 780}
   {:id 781 :word "island" :meaning "a land mass (smaller than a continent) that is surrounded by water" :rank 781}
   {:id 782 :word "record" :meaning "make a record of" :rank 782}
   {:id 783 :word "direct" :meaning "direct in spatial dimensions" :rank 783}
   {:id 784 :word "microsoft" :meaning "a large technology company known for the Windows operating system" :rank 784}
   {:id 785 :word "conference" :meaning "a prearranged meeting for consultation or exchange of information or discussion (especially one with a formal agenda)" :rank 785}
   {:id 786 :word "environment" :meaning "the totality of surrounding conditions" :rank 786}
   {:id 787 :word "records" :meaning "anything (such as a document or a phonograph record or a photograph) providing permanent evidence of or information about past events" :rank 787}
   {:id 788 :word "st" :meaning "short for street or saint" :rank 788}
   {:id 789 :word "district" :meaning "a region marked off for administrative or other purposes" :rank 789}
   {:id 790 :word "calendar" :meaning "a system of timekeeping that defines the beginning and length and divisions of the year" :rank 790}
   {:id 791 :word "costs" :meaning "pecuniary reimbursement to the winning party for the expenses of litigation" :rank 791}
   {:id 792 :word "style" :meaning "how something is done or how it happens" :rank 792}
   {:id 793 :word "url" :meaning "the address of a web page on the world wide web" :rank 793}
   {:id 794 :word "front" :meaning "the side that is forward or prominent" :rank 794}
   {:id 795 :word "statement" :meaning "a message that is stated or declared" :rank 795}
   {:id 796 :word "update" :meaning "modernize or bring up to date" :rank 796}
   {:id 797 :word "parts" :meaning "the local environment" :rank 797}
   {:id 798 :word "aug" :meaning "the month following July and preceding September" :rank 798}
   {:id 799 :word "ever" :meaning "at any time" :rank 799}
   {:id 800 :word "downloads" :meaning "transfer a file or program from a central computer to a smaller computer or to a computer at a remote location" :rank 800}
   {:id 801 :word "early" :meaning "at or near the beginning of a period of time or course of events or before the usual or expected time" :rank 801}
   {:id 802 :word "miles" :meaning "a unit of length equal to 1,760 yards or 5,280 feet" :rank 802}
   {:id 803 :word "sound" :meaning "appear in a certain way" :rank 803}
   {:id 804 :word "resource" :meaning "available source of wealth" :rank 804}
   {:id 805 :word "present" :meaning "temporal sense" :rank 805}
   {:id 806 :word "applications" :meaning "the act of bringing something to bear" :rank 806}
   {:id 807 :word "either" :meaning "after a negative statement used as an intensive meaning something like `likewise' or `also'" :rank 807}
   {:id 808 :word "ago" :meaning "in the past" :rank 808}
   {:id 809 :word "document" :meaning "writing that provides information (especially information of an official nature)" :rank 809}
   {:id 810 :word "word" :meaning "a unit of language that native speakers can identify" :rank 810}
   {:id 811 :word "works" :meaning "buildings for carrying on industrial labor" :rank 811}
   {:id 812 :word "material" :meaning "the tangible substance that goes into the makeup of a physical object" :rank 812}
   {:id 813 :word "bill" :meaning "a statute in draft before it becomes law" :rank 813}
   {:id 814 :word "apr" :meaning "the month following March and preceding May" :rank 814}
   {:id 815 :word "written" :meaning "set down in writing in any of various ways" :rank 815}
   {:id 816 :word "talk" :meaning "exchange thoughts" :rank 816}
   {:id 817 :word "federal" :meaning "national" :rank 817}
   {:id 818 :word "hosting" :meaning "be the host of or for" :rank 818}
   {:id 819 :word "rules" :meaning "a principle or condition that customarily governs behavior" :rank 819}
   {:id 820 :word "final" :meaning "occurring at or forming an end or termination" :rank 820}
   {:id 821 :word "adult" :meaning "a fully developed person from maturity onward" :rank 821}
   {:id 822 :word "tickets" :meaning "a commercial document showing that the holder is entitled to something (as to ride on public transportation or to enter a public entertainment)" :rank 822}
   {:id 823 :word "thing" :meaning "a special situation" :rank 823}
   {:id 824 :word "centre" :meaning "a low-lying region in central France" :rank 824}
   {:id 825 :word "requirements" :meaning "required activity" :rank 825}
   {:id 826 :word "via" :meaning "A main road or highway, especially in ancient Rome. (Mainly used in set phrases, below.)" :rank 826}
   {:id 827 :word "cheap" :meaning "relatively low in price or charging low prices" :rank 827}
   {:id 828 :word "nude" :meaning "a painting of a naked human figure" :rank 828}
   {:id 829 :word "kids" :meaning "a young person of either sex" :rank 829}
   {:id 830 :word "finance" :meaning "obtain or provide money for" :rank 830}
   {:id 831 :word "true" :meaning "consistent with fact or reality" :rank 831}
   {:id 832 :word "minutes" :meaning "a written account of what transpired at a meeting" :rank 832}
   {:id 833 :word "else" :meaning "(used only with indefinite or interrogative pronouns) Other; in addition to previously mentioned items." :rank 833}
   {:id 834 :word "mark" :meaning "attach a tag or label to" :rank 834}
   {:id 835 :word "third" :meaning "coming next after the second and just before the fourth in position" :rank 835}
   {:id 836 :word "rock" :meaning "a lump or mass of hard consolidated mineral matter" :rank 836}
   {:id 837 :word "gifts" :meaning "something acquired without compensation" :rank 837}
   {:id 838 :word "europe" :meaning "the 2nd smallest continent (actually a vast peninsula of Eurasia)" :rank 838}
   {:id 839 :word "reading" :meaning "the cognitive process of understanding a written linguistic message" :rank 839}
   {:id 840 :word "topics" :meaning "the subject matter of a conversation or discussion" :rank 840}
   {:id 841 :word "bad" :meaning "having undesirable or negative qualities" :rank 841}
   {:id 842 :word "individual" :meaning "a human being" :rank 842}
   {:id 843 :word "tips" :meaning "the extreme end of something" :rank 843}
   {:id 844 :word "plus" :meaning "a useful or valuable quality" :rank 844}
   {:id 845 :word "auto" :meaning "a motor vehicle with four wheels" :rank 845}
   {:id 846 :word "cover" :meaning "provide with a covering or cause to be covered" :rank 846}
   {:id 847 :word "usually" :meaning "under normal conditions" :rank 847}
   {:id 848 :word "edit" :meaning "prepare for publication or presentation by correcting, revising, or adapting" :rank 848}
   {:id 849 :word "together" :meaning "in contact with each other or in proximity" :rank 849}
   {:id 850 :word "videos" :meaning "the visible part of a television transmission" :rank 850}
   {:id 851 :word "percent" :meaning "a proportion in relation to a whole (which is usually the amount per hundred)" :rank 851}
   {:id 852 :word "fast" :meaning "acting or moving or capable of acting or moving quickly" :rank 852}
   {:id 853 :word "function" :meaning "the purpose or job of a person or thing" :rank 853}
   {:id 854 :word "fact" :meaning "a piece of information about circumstances that exist or events that have occurred" :rank 854}
   {:id 855 :word "unit" :meaning "any division of quantity accepted as a standard of measurement or exchange" :rank 855}
   {:id 856 :word "getting" :meaning "the act of acquiring something" :rank 856}
   {:id 857 :word "global" :meaning "involving the entire earth" :rank 857}
   {:id 858 :word "tech" :meaning "a school teaching mechanical and industrial arts and the applied sciences" :rank 858}
   {:id 859 :word "meet" :meaning "come together" :rank 859}
   {:id 860 :word "far" :meaning "to a considerable degree" :rank 860}
   {:id 861 :word "economic" :meaning "of or relating to an economy, the system of production and management of material wealth" :rank 861}
   {:id 862 :word "en" :meaning "a word meaning in or on in French and Spanish" :rank 862}
   {:id 863 :word "player" :meaning "a person who participates in or is skilled at some game" :rank 863}
   {:id 864 :word "projects" :meaning "any piece of work that is undertaken or attempted" :rank 864}
   {:id 865 :word "lyrics" :meaning "the text of a popular song or musical-comedy number" :rank 865}
   {:id 866 :word "often" :meaning "many times at short intervals" :rank 866}
   {:id 867 :word "subscribe" :meaning "offer to buy, as of stocks and shares" :rank 867}
   {:id 868 :word "submit" :meaning "refer for judgment or consideration" :rank 868}
   {:id 869 :word "germany" :meaning "a republic in central Europe" :rank 869}
   {:id 870 :word "amount" :meaning "a quantity of money" :rank 870}
   {:id 871 :word "watch" :meaning "look attentively" :rank 871}
   {:id 872 :word "included" :meaning "have as a part, be made up out of" :rank 872}
   {:id 873 :word "feel" :meaning "undergo an emotional sensation or be in a particular state of mind" :rank 873}
   {:id 874 :word "though" :meaning "(postpositive) however" :rank 874}
   {:id 875 :word "bank" :meaning "sloping land (especially the slope beside a body of water)" :rank 875}
   {:id 876 :word "risk" :meaning "expose to a chance of loss or damage" :rank 876}
   {:id 877 :word "thanks" :meaning "an acknowledgment of appreciation" :rank 877}
   {:id 878 :word "everything" :meaning "All the things under discussion." :rank 878}
   {:id 879 :word "deals" :meaning "a particular instance of buying or selling" :rank 879}
   {:id 880 :word "various" :meaning "of many different kinds purposefully arranged but lacking any uniformity" :rank 880}
   {:id 881 :word "words" :meaning "the words that are spoken" :rank 881}
   {:id 882 :word "linux" :meaning "an open-source version of the UNIX operating system" :rank 882}
   {:id 883 :word "jul" :meaning "short for July, the seventh month of the year" :rank 883}
   {:id 884 :word "production" :meaning "the act or process of producing something" :rank 884}
   {:id 885 :word "commercial" :meaning "connected with or engaged in or sponsored by or used in commerce or commercial enterprises" :rank 885}
   {:id 886 :word "james" :meaning "a Stuart king of Scotland who married a daughter of Henry VII" :rank 886}
   {:id 887 :word "weight" :meaning "the vertical force exerted by a mass as a result of gravity" :rank 887}
   {:id 888 :word "town" :meaning "an urban area with a fixed boundary that is smaller than a city" :rank 888}
   {:id 889 :word "heart" :meaning "the locus of feelings and intuitions" :rank 889}
   {:id 890 :word "advertising" :meaning "a public promotion of some product or service" :rank 890}
   {:id 891 :word "received" :meaning "get something" :rank 891}
   {:id 892 :word "choose" :meaning "pick out, select, or choose from a number of alternatives" :rank 892}
   {:id 893 :word "treatment" :meaning "care provided to improve a situation (especially medical procedures or applications that are intended to relieve illness or injury)" :rank 893}
   {:id 894 :word "newsletter" :meaning "report or open letter giving informal or confidential news of interest to a special group" :rank 894}
   {:id 895 :word "archives" :meaning "collection of records especially about an institution" :rank 895}
   {:id 896 :word "points" :meaning "plural of point; scores in a game" :rank 896}
   {:id 897 :word "knowledge" :meaning "the psychological result of perception and learning and reasoning" :rank 897}
   {:id 898 :word "magazine" :meaning "a periodic publication containing pictures and stories and articles of interest to those who purchase it or subscribe to it" :rank 898}
   {:id 899 :word "error" :meaning "a wrong action attributable to bad judgment or ignorance or inattention" :rank 899}
   {:id 900 :word "camera" :meaning "equipment for taking photographs (usually consisting of a lightproof box with a lens at one end and light-sensitive film at the other)" :rank 900}
   {:id 901 :word "jun" :meaning "short for June, the sixth month of the year" :rank 901}
   {:id 902 :word "girl" :meaning "a young woman" :rank 902}
   {:id 903 :word "currently" :meaning "at this time or period" :rank 903}
   {:id 904 :word "construction" :meaning "the act of constructing something" :rank 904}
   {:id 905 :word "toys" :meaning "an artifact designed to be played with" :rank 905}
   {:id 906 :word "registered" :meaning "record in writing" :rank 906}
   {:id 907 :word "clear" :meaning "readily apparent to the mind" :rank 907}
   {:id 908 :word "golf" :meaning "a game played on a large open course with 9 or 18 holes" :rank 908}
   {:id 909 :word "receive" :meaning "get something" :rank 909}
   {:id 910 :word "domain" :meaning "a particular environment or walk of life" :rank 910}
   {:id 911 :word "methods" :meaning "a way of doing something, especially a systematic way" :rank 911}
   {:id 912 :word "chapter" :meaning "a subdivision of a written work" :rank 912}
   {:id 913 :word "makes" :meaning "a recognizable kind" :rank 913}
   {:id 914 :word "protection" :meaning "the activity of protecting someone or something" :rank 914}
   {:id 915 :word "policies" :meaning "a plan of action adopted by an individual or social group" :rank 915}
   {:id 916 :word "loan" :meaning "the temporary provision of money (usually at interest)" :rank 916}
   {:id 917 :word "wide" :meaning "having great (or a certain) extent from one side to the other" :rank 917}
   {:id 918 :word "beauty" :meaning "the qualities that give pleasure to the senses" :rank 918}
   {:id 919 :word "manager" :meaning "someone who controls resources and expenditures" :rank 919}
   {:id 920 :word "india" :meaning "a republic in the Asian subcontinent in southern Asia" :rank 920}
   {:id 921 :word "position" :meaning "the particular portion of space occupied by something" :rank 921}
   {:id 922 :word "taken" :meaning "understood in a certain way" :rank 922}
   {:id 923 :word "sort" :meaning "a category of things distinguished by some common characteristic or quality" :rank 923}
   {:id 924 :word "listings" :meaning "a database containing an ordered array of items (names or topics)" :rank 924}
   {:id 925 :word "models" :meaning "a hypothetical description of a complex entity or process" :rank 925}
   {:id 926 :word "michael" :meaning "(Old Testament) the guardian archangel of the Jews" :rank 926}
   {:id 927 :word "known" :meaning "apprehended with certainty" :rank 927}
   {:id 928 :word "half" :meaning "one of two equal parts of a divisible whole" :rank 928}
   {:id 929 :word "cases" :meaning "an occurrence of something" :rank 929}
   {:id 930 :word "step" :meaning "any maneuver made as part of progress toward a goal" :rank 930}
   {:id 931 :word "engineering" :meaning "the practical application of science to commerce or industry" :rank 931}
   {:id 932 :word "florida" :meaning "a state in southeastern United States between the Atlantic and the Gulf of Mexico" :rank 932}
   {:id 933 :word "simple" :meaning "having few parts" :rank 933}
   {:id 934 :word "quick" :meaning "accomplished rapidly and without delay" :rank 934}
   {:id 935 :word "none" :meaning "not at all or in no way" :rank 935}
   {:id 936 :word "wireless" :meaning "medium for communication" :rank 936}
   {:id 937 :word "license" :meaning "a legal document giving official permission to do something" :rank 937}
   {:id 938 :word "paul" :meaning "United States feminist (1885-1977)" :rank 938}
   {:id 939 :word "friday" :meaning "the sixth day of the week" :rank 939}
   {:id 940 :word "lake" :meaning "a body of (usually fresh) water surrounded by land" :rank 940}
   {:id 941 :word "whole" :meaning "including all components without exception" :rank 941}
   {:id 942 :word "annual" :meaning "completing its life cycle within a year" :rank 942}
   {:id 943 :word "published" :meaning "prepared and printed for distribution and sale" :rank 943}
   {:id 944 :word "later" :meaning "happening at a time subsequent to a reference time" :rank 944}
   {:id 945 :word "basic" :meaning "pertaining to or constituting a base or basis" :rank 945}
   {:id 946 :word "sony" :meaning "Alternative spelling of sonny." :rank 946}
   {:id 947 :word "shows" :meaning "the act of publicly exhibiting or entertaining" :rank 947}
   {:id 948 :word "corporate" :meaning "of or belonging to a corporation" :rank 948}
   {:id 949 :word "google" :meaning "a widely used search engine that uses text-matching techniques to find web pages that are important and relevant to a user's search" :rank 949}
   {:id 950 :word "church" :meaning "one of the groups of Christians who have their own beliefs and forms of worship" :rank 950}
   {:id 951 :word "method" :meaning "a way of doing something, especially a systematic way" :rank 951}
   {:id 952 :word "purchase" :meaning "obtain by purchase" :rank 952}
   {:id 953 :word "customers" :meaning "someone who pays for goods or services" :rank 953}
   {:id 954 :word "active" :meaning "chemical agent capable of activity" :rank 954}
   {:id 955 :word "response" :meaning "a result" :rank 955}
   {:id 956 :word "practice" :meaning "a customary way of operation or behavior" :rank 956}
   {:id 957 :word "hardware" :meaning "major items of military weaponry (as tanks or missile)" :rank 957}
   {:id 958 :word "figure" :meaning "a diagram or picture illustrating textual material" :rank 958}
   {:id 959 :word "materials" :meaning "the tangible substance that goes into the makeup of a physical object" :rank 959}
   {:id 960 :word "fire" :meaning "the event of something burning (often destructive)" :rank 960}
   {:id 961 :word "holiday" :meaning "leisure time away from work devoted to rest or pleasure" :rank 961}
   {:id 962 :word "chat" :meaning "talk socially without exchanging too much information" :rank 962}
   {:id 963 :word "enough" :meaning "as much as necessary" :rank 963}
   {:id 964 :word "designed" :meaning "make or work out a plan for" :rank 964}
   {:id 965 :word "along" :meaning "with a forward motion" :rank 965}
   {:id 966 :word "among" :meaning "Denotes a mingling or intermixing with distinct or separable objects. (See Usage Note at amidst.)" :rank 966}
   {:id 967 :word "death" :meaning "the event of dying or departure from life" :rank 967}
   {:id 968 :word "writing" :meaning "the act of creating written works" :rank 968}
   {:id 969 :word "speed" :meaning "distance travelled per unit time" :rank 969}
   {:id 970 :word "html" :meaning "a set of tags and rules (conforming to SGML) for using them in developing hypertext documents" :rank 970}
   {:id 971 :word "countries" :meaning "a politically organized body of people under a single government" :rank 971}
   {:id 972 :word "loss" :meaning "something that is lost" :rank 972}
   {:id 973 :word "face" :meaning "the front of the human head from the forehead to the chin and ear to ear" :rank 973}
   {:id 974 :word "brand" :meaning "a name given to a product or service" :rank 974}
   {:id 975 :word "discount" :meaning "bar from attention or consideration" :rank 975}
   {:id 976 :word "higher" :meaning "advanced in complexity or elaboration" :rank 976}
   {:id 977 :word "effects" :meaning "property of a personal character that is portable but not used in business" :rank 977}
   {:id 978 :word "created" :meaning "make or cause to be or to become" :rank 978}
   {:id 979 :word "remember" :meaning "recall knowledge from memory" :rank 979}
   {:id 980 :word "standards" :meaning "a basis for comparison" :rank 980}
   {:id 981 :word "oil" :meaning "a slippery or viscous liquid or liquefiable substance not miscible with water" :rank 981}
   {:id 982 :word "bit" :meaning "a small piece or quantity of something" :rank 982}
   {:id 983 :word "yellow" :meaning "of the color intermediate between green and orange in the color spectrum" :rank 983}
   {:id 984 :word "political" :meaning "involving or characteristic of politics or parties or politicians" :rank 984}
   {:id 985 :word "increase" :meaning "become bigger or greater in amount" :rank 985}
   {:id 986 :word "advertise" :meaning "call attention to" :rank 986}
   {:id 987 :word "kingdom" :meaning "a domain in which something is dominant" :rank 987}
   {:id 988 :word "base" :meaning "use as a basis for" :rank 988}
   {:id 989 :word "near" :meaning "not far distant in time or space or degree or circumstances" :rank 989}
   {:id 990 :word "environmental" :meaning "of or relating to the external conditions or surroundings" :rank 990}
   {:id 991 :word "thought" :meaning "the content of cognition" :rank 991}
   {:id 992 :word "stuff" :meaning "the tangible substance that goes into the makeup of a physical object" :rank 992}
   {:id 993 :word "french" :meaning "of or pertaining to France or the people of France" :rank 993}
   {:id 994 :word "storage" :meaning "the act of storing something" :rank 994}
   {:id 995 :word "oh" :meaning "used to express surprise, understanding, or pain" :rank 995}
   {:id 996 :word "japan" :meaning "a string of more than 3,000 islands to the east of Asia extending 1,300 miles between the Sea of Japan and the western Pacific Ocean" :rank 996}
   {:id 997 :word "doing" :meaning "engage in" :rank 997}
   {:id 998 :word "loans" :meaning "the temporary provision of money (usually at interest)" :rank 998}
   {:id 999 :word "shoes" :meaning "a particular situation" :rank 999}
   {:id 1000 :word "entry" :meaning "an item inserted in a written record" :rank 1000}
   ])
