import scrapy
import csv


class HomezzSpider(scrapy.Spider):
    name = 'homezz'
    start_urls = [
        # 'https://homezz.ro/anunturi_apartamente_de-vanzare_in-iasi-is.html',
        'https://homezz.ro/anunturi_case-vile_de-vanzare_in-iasi-is.html'
    ]
    page_no = 0  # current page
    MAX_NO_OF_PAGES = 28

    def __init__(self, **kwargs):
        super().__init__(**kwargs)

        # initializing CSV file
        with open('homezz_data.csv', "w+") as data:
            writer = csv.writer(data, delimiter=',', quotechar='"', quoting=csv.QUOTE_MINIMAL, lineterminator='\n')
            writer.writerow(
                ['tip_proprietate', 'tip_tranzactie', 'nr_camere', 'suprafata', 'suprafata_teren', 'an_constructie',
                 'zona', 'pret', 'link'])

    def parse(self, response):
        HomezzSpider.page_no += 1

        # debug file
        with open('homezz_out.txt', 'wb') as debug:

            posts = response.css('#list_cart_holder a')
           # debug.write(posts.get().encode('utf-8'))

            for post in posts:
                post_url = post.css('::attr(href)').get()  # getting link to post
                yield scrapy.Request(post_url, callback=self.parse_listing)

            next_page_url = response.css('.next_page a::attr(href)').get()  # works!

            debug.write(next_page_url.encode('utf-8'))

            if HomezzSpider.page_no < HomezzSpider.MAX_NO_OF_PAGES:
                yield scrapy.Request(next_page_url.encode('utf-8').decode(), callback=self.parse)

    @staticmethod
    def parse_listing(response):
        with open('homezz_out.txt', 'ab') as debug:

            # div with all the details of the listing
            detalii_div = response.css('#extra-fields')

            # salvam:
            tip_proprietate = 'CAS'
            tip_tranzactie = 'CMP'
            nr_camere = ''
            suprafata = ''
            suprafata_teren = ''
            an = ''
            zona = ''

            pret = response.css('#price::text').get().replace(' ', '').replace('.', '')
            if response.css("#price b::text").get() == 'eur':
                pass
            elif response.css("#price b::text").get() == 'ron':
                pret = '%s' % (int(pret) // 5)
            else:
                return

            # debug.write((response.url + '\n').encode('utf-8'))
            # debug.write(pret.encode('utf-8'))
            for div in detalii_div.css('div'):
                titlu = div.css('span::text').get()
                val = div.css('h1,h2,h3,h4,h5,h6').css('a::text').get()
                if val is None:
                    val = div.css('h1,h2,h3,h4,h5,h6').css('::text').get()
                # debug.write((titlu + '\n').encode('utf-8'))
                #
                # debug.write(((val if val is not None else 'none') + '\n').encode('utf-8'))

                if val is None:
                    continue

                if 'zon' in titlu.lower():
                    zona = val.lower().replace(' ', '-')

                if 'camere' in titlu:
                    nr_camere = val

                if 'util' in titlu:  # suprafata utila
                    suprafata = val[:-3].replace(',', '.')
                elif suprafata == '' and 'suprafa' in titlu:
                    suprafata = val[:-3].replace(',', '.')

                if 'teren' in titlu:
                    suprafata_teren = val[:-3].replace(',', '.')

                if 'construc' in titlu:
                    if 'ntre' in val:
                        # Intre xxxx si yyyy
                        x = int(val[6:10])
                        y = int(val[14:18])
                        an = str((x + y) // 2)
                    else:
                        an = val[:4]

            if suprafata_teren == '':
                suprafata_teren = suprafata

            if an == '':
                return

            if nr_camere == '':
                nr_camere = '1'
                debug.write(("can't find nr camere for APT,{},{},{},{},{},{}: "
                             .format(nr_camere, suprafata, suprafata_teren, an, zona, pret) +
                             response.url + "\n").encode('utf-8'))

            if suprafata == '':
                suprafata = '40'
                debug.write(("can't find suprafata for APT,{},{},{},{},{},{}: "
                             .format(nr_camere, suprafata, suprafata_teren, an, zona, pret) +
                             response.url + "\n").encode('utf-8'))

            # writing data in CSV
            with open('homezz_data.csv', "a") as data:
                writer = csv.writer(data, delimiter=',', quotechar='"', quoting=csv.QUOTE_MINIMAL, lineterminator='\n')
                writer.writerow(
                    [tip_proprietate, tip_tranzactie, nr_camere, suprafata, suprafata_teren, an, zona, pret,
                     response.url])

            # debug.write(
            #     (nr_camere + ' ' + an + ' ' + suprafata + ' ' + suprafata_teren + ' ' + pret + ' ' + zona + '\n')
            #     .encode('utf-8'))
