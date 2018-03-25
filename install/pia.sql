-- phpMyAdmin SQL Dump
-- version 4.2.12deb2+deb8u2
-- http://www.phpmyadmin.net
--
-- Počítač: localhost
-- Vytvořeno: Úte 09. led 2018, 19:28
-- Verze serveru: 5.6.30-1~bpo8+1-log
-- Verze PHP: 5.6.30-0+deb8u1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Databáze: `pia`
--

-- --------------------------------------------------------

--
-- Struktura tabulky `vaverkaj_friends`
--

CREATE TABLE IF NOT EXISTS `vaverkaj_friends` (
  `friendId` bigint(20) NOT NULL,
  `personId` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Vypisuji data pro tabulku `vaverkaj_friends`
--

INSERT INTO `vaverkaj_friends` (`friendId`, `personId`) VALUES
(1, 5),
(5, 1),
(1, 2),
(2, 1),
(4, 3),
(5, 3),
(2, 3),
(3, 4),
(3, 5),
(3, 2);

-- --------------------------------------------------------

--
-- Struktura tabulky `vaverkaj_post`
--

CREATE TABLE IF NOT EXISTS `vaverkaj_post` (
`id` bigint(20) NOT NULL,
  `content` longtext,
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `user_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;

--
-- Vypisuji data pro tabulku `vaverkaj_post`
--

INSERT INTO `vaverkaj_post` (`id`, `content`, `timestamp`, `user_id`) VALUES
(1, 'Já vím, že kobliha je takové zprofanované pečivo, dneska je ale pro mě ukázkou toho, že život je fajn. Dostala jsem jí totiž k snídani od Káťa Jirsová, prostě jen tak, aby byl čas v práci o koblihu lepší. Mé životní nadšení podpořili také Lenka W. Cisarova a Ladislav Zibura i Jan Dvořáček, kteří věnovali svůj čas, aby pomohli dobrým věcem. Je radost mít takové lidi kolem. Děkuju ❤️', '2018-01-09 17:55:40', 1),
(2, 'Pozval jsem k nám do baráku rozumnýho kandidáta, kterej by jako prezident rozhodně ostudu nedělal! Držím palce!', '2018-01-09 17:57:40', 2),
(3, 'Poslední průzkum před 1. kolem. A hlavně v pátek a sobotu přijďte!', '2018-01-09 17:58:45', 2),
(4, 'Po tomhle prohlášení by měl Mynář panu prezidentovi vyřídit inkartu, ať to pak vlakem na Vysočinu nestojí majlant\r\n\r\nhttp://instagram.com/choco_afro', '2018-01-09 17:59:03', 2),
(5, 'I letos bych čas od času chtěl napsat něco o knížkách. Sám nečtu tolik, kolik bych chtěl. Ale docela dost čtu o psaní, takže se občas dozvím něco za zákulisí.\r\n\r\nNedávno jsem se na Český rozhlas Vltava doslechl, že pravidelně čtou beletrii jen zhruba 4 % Čechů. To shodou okolností zhruba odpovídá procentu lidí, kteří poslouchají vážnou hudbu (to mám zase z TEDx přednášky dirigenta Benjamina Zandera).', '2018-01-09 17:59:27', 1),
(6, 'Do diskuze o důležitosti čtení se pouštět nechci. Ani nevím, jak si Češi stojí v porovnání se světem. Každopádně si myslím, že je čtení přinejmenším milý koníček, který rozšiřuje obzory a slovní zásobu. A dodnes platí, že knihy představují asi nejdůležitější zdroj našich informací o historii.', '2018-01-09 17:59:32', 1),
(7, '8 dní do voleb', '2018-01-09 17:59:48', 2),
(8, 'You can bring on the first page or any other page, for the new visitors, the old content.It’s no need of the question why should I do this because you want that your visitors could have in their view your older articles.', '2018-01-09 18:06:38', 3),
(9, 'Hlavním zdrojem byl tehdejší vedoucí stratég Bílého domu Steve Bannon. S prezidentem se Wolff osobně zná od 90. let. „Volával mi často a nadával buďto mně, nebo si stěžoval na druhé,“ přiznává známý publicista, sloupkař a komentátor. Přesto je s podivem, že to s Trumpem dokázal takto sehrát, protože se o něm vědělo, že před více než deseti lety provedl podobný kousek končící veřejnými indiskrecemi i mediálnímu magnátovi Rupertu Murdochovi. Bylo by tudíž jen logické, že si na něj lidé okolo prezidenta a hlavně on sám měli dát veliký pozor. To se ale nestalo.', '2018-01-09 18:08:34', 4),
(10, 'Other plug-ins are already doing a great job about random posts, but for me was not important to show just a list of a few links to my articles; not just a random generated list, with some titles. For some new visitors, the article’s content is more important than the title, actually, for anybody this statement is true .\r\nSo, I created this plugin to mark out even some text from the random article.', '2018-01-09 18:09:31', 3),
(11, 'Prý si nedělá starosti se zdrojováním svých textů a především jde po pikantních informacích. Kniha o Trumpovi zcela jistě obsahuje mnoho nepřesností, ve výsledku však přináší podle recenzentů velmi pravděpodobný obraz dysfunkčních zmatků v dnešním Bílém domě.', '2018-01-09 18:09:53', 3),
(12, 'Jégou s týmem francouzských a dánských odborníků zkoumal účinky na zdraví, jestliže budoucí matka užívala jeden ze tří léků na bolest: aspirin, acetaminofen (paracetamol) a ibuprofen. Tyto dřívější experimenty ukázaly, že když je během těhotenství užívaly, všechna tři léčiva nějakým způsobem ovlivnily varlata narozených chlapců. Nejvíce právě ibuprofen.', '2018-01-09 18:10:27', 4),
(13, 'Na videu je patrné, jak policisté prohrabávají odpad v popelářském vozu, až naleznou ruku šťastného muže, který si jejich pomoc zavolal díky mobilnímu telefonu. Zesláblý bezdomovec po objevení potřeboval pomoc lékařů, kteří k němu přivezli nosítka. O chvíli později jej převezli do nemocnice.', '2018-01-09 18:10:58', 4),
(14, 'Do kontejneru na papírový odpad se dostal v pátek večer poblíž centra města Kroměříž. Údajně se tam přišel ohřát. „Uvnitř pak popíjel alkohol a nakonec usnul. Zahrabal se pod papír, a tak ho nebylo vidět. V sobotu ráno obsah kontejneru skončil v popelářském voze i s nocležníkem. Ten měl u sebe naštěstí mobilní telefon a v těsném prostoru se mu podařilo vytáhnout ho z kapsy a zavolat na linku 158,“ podotkla policejní mluvčí.', '2018-01-09 18:11:20', 3),
(15, 'Norishige Kanai wrote on social media he was worried he would not fit into the seat of the Russian Soyuz vehicle that is due to bring him home in June.\r\n\r\nAstronauts grow an average of between two and five centimetres in space.\r\n\r\nThis is because of the absence of gravity which allows the vertebrae in their spines to spread apart.\r\n\r\nMr Kanai tweeted: "Good morning, everybody. I have a major announcement today. We had our bodies measured after reaching space, and wow, wow, wow, I had actually grown by as much as 9cm!\r\n\r\n"I grew like some plant in just three weeks. Nothing like this since high school. I''m a bit worried whether I''ll fit in the Soyuz seat when I go back."', '2018-01-09 18:20:39', 5),
(16, '"Waist-deep" mudslides in areas scorched by wildfires last month shut down more than 30 miles (48km) of the main coastal highway, officials say.\r\n\r\nEmergency crews are working to rescue multiple people trapped in vehicles and homes, a Santa Barbara official said.\r\n\r\nAt least eight people were injured and thousands have fled ahead of the rain.\r\n\r\nHeavy rain run-off caused waist-deep mudflow in the Montecito, where some homes were knocked from their foundations, Santa Barbara County Fire Department spokesman Mike Eliason told the Los Angeles Times newspaper.', '2018-01-09 18:21:23', 3),
(17, 'Astronauts can grow while in space and return to a normal height when they go back to Earth.\r\n\r\n"Nine centimetres is a lot, but it is possible, knowing that every human body is different," Libby Jackson of the UK Space Agency told BBC News.\r\n\r\n"You do get taller in space as your spine drifts apart, usually by about two to five centimetres.', '2018-01-09 18:21:32', 5),
(18, 'The street has been named Fahreddin Pasha Road, after an Ottoman military commander the UAE foreign minister appeared to criticise online.\r\n\r\nAbdullah bin Zayed al Nahyan shared a tweet that made allegations about Pasham and Turkish President Recep Tayyip Erdogan''s ancestors.', '2018-01-09 18:21:39', 5),
(19, 'The original tweet suggested Pasham had mistreated Arabs while serving as governor of the holy Islamic city of Medina in the early 20th Century, and suggested his forces were related to Mr Erdogan.\r\n\r\nThe Turkish president defended the former governor, questioning the UAE minister over his own ancestral heritage.\r\n', '2018-01-09 18:21:47', 5),
(20, 'Relations between the nations have been strained for months since Turkey sided with Qatar in a diplomatic dispute between the Gulf nations over alleged support for Islamic extremism.', '2018-01-09 18:21:54', 3),
(21, '"As of now, the embassy''s address will feature on official correspondence as Defender of Medina Street, Fahreddin Pasha Road. Good luck with it," Mustafa Tuna said in a tweet, in Turkish.', '2018-01-09 18:21:59', 3),
(22, 'The slow-release tablet could free patients from having to take daily medication, they say.\r\n\r\nIt looks like a normal capsule, but on reaching the stomach its coating dissolves and a special structure packed inside unfolds.\r\n\r\nThis 4cm (1.5in) star-shaped scaffold stays in the stomach for seven days, steadily releasing its cargo of drugs.', '2018-01-09 18:22:25', 3),
(23, 'Once it has delivered its payload, the star begins to degrade and passes on through the digestive tract.\r\n\r\nIn the pig trial, the researchers dosed it with enough of three antiretroviral drugs - dolutegravir, rilpivirine and cabotegravir - to last for seven days.', '2018-01-09 18:22:32', 3),
(24, 'Santa Barbara County Sheriff''s Office spokeswoman Kelly Huber told the newspaper two people were found dead on Tuesday in Montecito and may have been killed as result of the storm.\r\n\r\nWildfires in December, including the Thomas Fire, swept through the area burning vegetation that helps prevent flooding and landslides.\r\n\r\nThousands of California residents were asked to evacuate on Monday for the second time in two months.', '2018-01-09 18:23:15', 5),
(25, 'Čas od času se mi s žádostí o podporu ozve nějaká politická strana nebo neziskovka. Prezidentské volby mě pak postavily před úplně novou věc.\r\n\r\nO podporu mě požádali hned dva kandidáti. Jeden prostřednictvím svých poradců, s druhým jsem se pak dvakrát setkal osobně.\r\n\r\nCo vám budu povídat, je to opojný. Je vám 25 let, sedíte u dobrýho oběda s jedním z favoritů voleb a mluvíte o tom, kam by se podle vás měla republika ubírat.', '2018-01-09 18:24:09', 1),
(26, 'ivermectin and the star remaining in the stomach for up to two weeks.\r\n\r\nResearcher Giovanni Traverso, from Massachusetts Institute of Technology and Brigham and Women''s Hospital in the US, said: "We wanted to come up with a system to make it easier for patients to stick to taking their treatments.\r\n\r\n"Changing a medication so it only needs to be taken once a week rather than once a day should be more convenient and improve compliance.\r\n\r\n"Once-a-month formulations might even be possible for some diseases."', '2018-01-09 18:24:18', 5),
(27, 'A company called Lyndra is now developing the technology and plans human trials of the long-lasting oral delivery pills within the next 12 months. Tests with HIV medication could begin after that, subject to approval and more animal tests.\r\n\r\nDr Traverso said: "There are lots of patients this could help, including people with dementia or mental health disorders such as schizophrenia."\r\n\r\nSome slow-release drugs could already be given by injection, he said.', '2018-01-09 18:24:26', 5),
(28, 'Nu, a přesně v takových chvílích je zapotřebí použít soudnost a zdravý rozum. Mediální dosah (byť jenom na sítích) totiž s sebou má přinášet především zodpovědnost.\r\n\r\nMoje odpověď tak byla v obou případech stejná.', '2018-01-09 18:24:38', 1),
(29, 'Necítím se jako člověk, který by někomu měl radit, koho má nebo nemá volit. Chci se vyjadřovat jen k věcem, které já sám vytvářím, rozumím jim nebo s nimi alespoň mám osobní zkušenost.\r\n\r\nŽe jsem byl párkrát v televizi ze mě nedělá o nic většího odborníka, než je moje máma nebo babička. Rozumné podle mě je naslouchat hlasům lidí, kteří danému tématu rozumí.', '2018-01-09 18:24:43', 1),
(30, 'Čau lidi, je to v pohodě, nemusíte to číst, takže vám to nemusíme ukázat. Ukazuje Kalúsek svoje zprávy? Zrovna nám na farmě porodila prasnice Matylda. Koukejte na ta prasátka. A máme i lemury, hele jak dovádějí. Žádná korupce nebyla. Mafie. Vezměte děti na výlet.', '2018-01-09 18:25:46', 2);

-- --------------------------------------------------------

--
-- Struktura tabulky `vaverkaj_postLikes_first`
--

CREATE TABLE IF NOT EXISTS `vaverkaj_postLikes_first` (
  `vaverkaj_post_id` bigint(20) NOT NULL,
  `first_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struktura tabulky `vaverkaj_postLikes_second`
--

CREATE TABLE IF NOT EXISTS `vaverkaj_postLikes_second` (
  `vaverkaj_post_id` bigint(20) NOT NULL,
  `second_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struktura tabulky `vaverkaj_postLikes_third`
--

CREATE TABLE IF NOT EXISTS `vaverkaj_postLikes_third` (
  `vaverkaj_post_id` bigint(20) NOT NULL,
  `third_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struktura tabulky `vaverkaj_requests`
--

CREATE TABLE IF NOT EXISTS `vaverkaj_requests` (
  `fromId` bigint(20) NOT NULL,
  `toId` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Vypisuji data pro tabulku `vaverkaj_requests`
--

INSERT INTO `vaverkaj_requests` (`fromId`, `toId`) VALUES
(5, 4),
(5, 2);

-- --------------------------------------------------------

--
-- Struktura tabulky `vaverkaj_user`
--

CREATE TABLE IF NOT EXISTS `vaverkaj_user` (
`id` bigint(20) NOT NULL,
  `date` date DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `male` bit(1) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `profileImage` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

--
-- Vypisuji data pro tabulku `vaverkaj_user`
--

INSERT INTO `vaverkaj_user` (`id`, `date`, `email`, `male`, `password`, `profileImage`, `username`) VALUES
(1, '1986-12-08', 'lad.zib@seznam.cz', b'1', '1000:0bf09922071ac1924317b2153f8d2d8cba409ec74d9184c1:4f80c84c8f3d51e304afbed81d97705b3dbc2b8e1b6eaf24', 'DY2uUcmE.jpg', 'Ladislav Zibura'),
(2, '1951-06-13', 'domfer@google.com', b'1', '1000:39efb00d4e9642ddddf9ecf165dc149fd84e5ee73315863e:9eec350e7e6d901bd40357a26e786eb937f2ab84c7419a9a', 'U3sGWu9I.jpg', 'Dominik Feri'),
(3, '1976-01-09', 'user1@test.com', b'0', '1000:348210e42ec0cfdd908d2a669bc485900c135e84db47d531:89bf3627ac37897ba11cc82621abfc67fef2b7c69fcf2967', 'uBP4SDZj.jpg', 'user1'),
(4, '2008-06-12', 'user22@seznam.cz', b'1', '1000:f065e2c2647cf0d17eb5e2c99778c41fb03cec1e294e1f8a:737bfc2d2fdcf4093bd0ce83f90fcf7b7b5e815ba07ff9f2', NULL, 'user2'),
(5, '2008-07-17', 'user2324@emailc.cz', b'1', '1000:6e51ca6705ff673a68561c44afd7af0ee71f548cb45ed25a:edc3c4986d6d6e7b1a353ecb986e376b0b4a748d58d74317', 'SV7DaDTK.jpg', 'user3');

--
-- Klíče pro exportované tabulky
--

--
-- Klíče pro tabulku `vaverkaj_friends`
--
ALTER TABLE `vaverkaj_friends`
 ADD KEY `FK_2tew3k7v463t8jriso85gy0av` (`personId`), ADD KEY `FK_myya4thv2kxaa5tm7paqtxf1r` (`friendId`);

--
-- Klíče pro tabulku `vaverkaj_post`
--
ALTER TABLE `vaverkaj_post`
 ADD PRIMARY KEY (`id`), ADD KEY `FK_bel7dxud854ft8ys6m3kxswmt` (`user_id`);

--
-- Klíče pro tabulku `vaverkaj_postLikes_first`
--
ALTER TABLE `vaverkaj_postLikes_first`
 ADD KEY `FK_3vossu0t0orpryic1yhnsbqsn` (`first_id`), ADD KEY `FK_igkxl41uoscqu8y890isbm71v` (`vaverkaj_post_id`);

--
-- Klíče pro tabulku `vaverkaj_postLikes_second`
--
ALTER TABLE `vaverkaj_postLikes_second`
 ADD KEY `FK_bnsx7y6kijs7ti9sfxvgvsod6` (`second_id`), ADD KEY `FK_5gf07sg3jmfq4li0ittv5mmtt` (`vaverkaj_post_id`);

--
-- Klíče pro tabulku `vaverkaj_postLikes_third`
--
ALTER TABLE `vaverkaj_postLikes_third`
 ADD KEY `FK_bm46skl5i1kio16818o2qlg19` (`third_id`), ADD KEY `FK_8xoj45b8jj6ikalsjam51ycvg` (`vaverkaj_post_id`);

--
-- Klíče pro tabulku `vaverkaj_requests`
--
ALTER TABLE `vaverkaj_requests`
 ADD KEY `FK_qmicoohcbun6w7368n9j2qmvk` (`toId`), ADD KEY `FK_lvy1p8mkpev4pj6iqf2ah8w9t` (`fromId`);

--
-- Klíče pro tabulku `vaverkaj_user`
--
ALTER TABLE `vaverkaj_user`
 ADD PRIMARY KEY (`id`), ADD UNIQUE KEY `UK_mpy0l7bg900g3ulrf1jkyofq9` (`username`);

--
-- AUTO_INCREMENT pro tabulky
--

--
-- AUTO_INCREMENT pro tabulku `vaverkaj_post`
--
ALTER TABLE `vaverkaj_post`
MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=31;
--
-- AUTO_INCREMENT pro tabulku `vaverkaj_user`
--
ALTER TABLE `vaverkaj_user`
MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=6;
--
-- Omezení pro exportované tabulky
--

--
-- Omezení pro tabulku `vaverkaj_friends`
--
ALTER TABLE `vaverkaj_friends`
ADD CONSTRAINT `FK_2tew3k7v463t8jriso85gy0av` FOREIGN KEY (`personId`) REFERENCES `vaverkaj_user` (`id`),
ADD CONSTRAINT `FK_myya4thv2kxaa5tm7paqtxf1r` FOREIGN KEY (`friendId`) REFERENCES `vaverkaj_user` (`id`);

--
-- Omezení pro tabulku `vaverkaj_post`
--
ALTER TABLE `vaverkaj_post`
ADD CONSTRAINT `FK_bel7dxud854ft8ys6m3kxswmt` FOREIGN KEY (`user_id`) REFERENCES `vaverkaj_user` (`id`);

--
-- Omezení pro tabulku `vaverkaj_postLikes_first`
--
ALTER TABLE `vaverkaj_postLikes_first`
ADD CONSTRAINT `FK_3vossu0t0orpryic1yhnsbqsn` FOREIGN KEY (`first_id`) REFERENCES `vaverkaj_user` (`id`),
ADD CONSTRAINT `FK_igkxl41uoscqu8y890isbm71v` FOREIGN KEY (`vaverkaj_post_id`) REFERENCES `vaverkaj_post` (`id`);

--
-- Omezení pro tabulku `vaverkaj_postLikes_second`
--
ALTER TABLE `vaverkaj_postLikes_second`
ADD CONSTRAINT `FK_5gf07sg3jmfq4li0ittv5mmtt` FOREIGN KEY (`vaverkaj_post_id`) REFERENCES `vaverkaj_post` (`id`),
ADD CONSTRAINT `FK_bnsx7y6kijs7ti9sfxvgvsod6` FOREIGN KEY (`second_id`) REFERENCES `vaverkaj_user` (`id`);

--
-- Omezení pro tabulku `vaverkaj_postLikes_third`
--
ALTER TABLE `vaverkaj_postLikes_third`
ADD CONSTRAINT `FK_8xoj45b8jj6ikalsjam51ycvg` FOREIGN KEY (`vaverkaj_post_id`) REFERENCES `vaverkaj_post` (`id`),
ADD CONSTRAINT `FK_bm46skl5i1kio16818o2qlg19` FOREIGN KEY (`third_id`) REFERENCES `vaverkaj_user` (`id`);

--
-- Omezení pro tabulku `vaverkaj_requests`
--
ALTER TABLE `vaverkaj_requests`
ADD CONSTRAINT `FK_lvy1p8mkpev4pj6iqf2ah8w9t` FOREIGN KEY (`fromId`) REFERENCES `vaverkaj_user` (`id`),
ADD CONSTRAINT `FK_qmicoohcbun6w7368n9j2qmvk` FOREIGN KEY (`toId`) REFERENCES `vaverkaj_user` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
