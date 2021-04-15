import scrapy
import csv


class StoriaChiriiSpider(scrapy.Spider):
    name = 'storia_chirii'
    start_urls = [
        # 'https://www.storia.ro/inchiriere/apartament/iasi/iasi/',
        'https://www.storia.ro/inchiriere/casa/iasi/iasi/'
    ]
    page_no = 0  # current page
    MAX_NO_OF_PAGES = 65

    def __init__(self, **kwargs):
        super().__init__(**kwargs)

        # initializing CSV file
        with open('storia_chirii_data.csv', "w+") as data:
            writer = csv.writer(data, delimiter=',', quotechar='"', quoting=csv.QUOTE_MINIMAL, lineterminator='\n')
            writer.writerow(
                ['tip_proprietate', 'tip_tranzactie', 'nr_camere', 'suprafata', 'suprafata_teren', 'an_constructie',
                 'zona', 'pret', 'link'])

    def parse(self, response):
        StoriaChiriiSpider.page_no += 1

        # debug file
        with open('storia_chirii_out.txt', 'wb') as debug:
            divs = response.css('div.col-md-content.section-listing__row-content article.offer-item')

            for post in divs:
                post_url = post.css('h3 a::attr(href)').get()  # getting link to post
                yield scrapy.Request(post_url, callback=self.parse_listing)

        next_page_url = response.css('li.pager-next a::attr(href)').get()  # works!
        if StoriaChiriiSpider.page_no < StoriaChiriiSpider.MAX_NO_OF_PAGES:
            yield scrapy.Request(next_page_url.encode('utf-8').decode(), callback=self.parse)

    @staticmethod
    def parse_listing(response):
        with open('storia_chirii_out.txt', 'ab') as debug:

            # div with all the details of the listing
            detalii_div = response.css('div.css-1d9dws4.e1dlfs272')

            # salvam:
            tip_proprietate = 'CAS'
            tip_tranzactie = 'INC'
            nr_camere = ''
            suprafata = ''
            suprafata_teren = ''
            an = ''
            try:
                zona = response.css('div.css-nc8v92 a')[5].css('::text').get().lower().replace(' ', '-')
            except IndexError:
                debug.write((response.url + '\n').encode('utf-8'))

                return
            pret = response.css('strong[aria-label=Preț]::text').get()[:-2].replace(' ', '')

            for div in detalii_div.css('div[role=region]'):
                titlu = div.css('::attr(aria-label)').get()
                val = div.css('.css-1ytkscc.ecjfvbm0::attr(title)').get()

                if 'camere' in titlu:
                    nr_camere = val

                if 'util' in titlu:  # suprafata utila
                    suprafata = val.replace(',', '.')
                elif suprafata == '' and 'suprafa' in titlu:
                    suprafata = val[:-3].replace(',', '.')

                if 'teren' in titlu:
                    suprafata_teren = val[:-3].replace(',', '.')

                if 'Anul construc' in titlu or 'anul construc' in titlu:
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
            with open('storia_chirii_data.csv', "a") as data:
                writer = csv.writer(data, delimiter=',', quotechar='"', quoting=csv.QUOTE_MINIMAL, lineterminator='\n')
                writer.writerow(
                    [tip_proprietate, tip_tranzactie, nr_camere, suprafata, suprafata_teren, an, zona, pret,
                     response.url])

            # debug.write(
            #     (nr_camere + ' ' + an + ' ' + suprafata + ' ' + suprafata_teren + ' ' + pret + ' ' + zona + '\n')
            #     .encode('utf-8'))
