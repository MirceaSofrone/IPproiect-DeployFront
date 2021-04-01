import csv

import scrapy


class ImobiliareSpider(scrapy.Spider):
    name = 'imobiliare'
    start_urls = [
        # 'https://www.imobiliare.ro/vanzare-apartamente/iasi'
        'https://www.imobiliare.ro/vanzare-case-vile/iasi?id=172571550'
    ]
    page_no = 0  # current page
    MAX_NO_OF_PAGES = 33

    def __init__(self, **kwargs):
        super().__init__(**kwargs)

        # initializing CSV file
        with open('imobiliare_data.csv', "w+") as data:
            writer = csv.writer(data, delimiter=',', quotechar='"', quoting=csv.QUOTE_MINIMAL, lineterminator='\n')
            writer.writerow(
                ['tip_proprietate', 'nr_camere', 'suprafata', 'suprafata_teren', 'an_constructie', 'zona', 'pret'])

    def parse(self, response, **kwargs):
        ImobiliareSpider.page_no += 1

        with open('imobiliare_out.txt', 'wb') as debug:
            divs = response.xpath('//div[@itemtype]')

            for post in divs:
                url = post.css('a.img-block::attr(href)').get()  # getting link to post
                yield scrapy.Request(url, callback=self.parse_listing)

        next_page_url = response.css('a.inainte.butonpaginare::attr(href)').get()
        if ImobiliareSpider.page_no < ImobiliareSpider.MAX_NO_OF_PAGES:
            yield scrapy.Request(next_page_url.encode('utf-8').decode(), callback=self.parse)

    @staticmethod
    def parse_listing(response):
        with open('imobiliare_out.txt', 'ab') as f:
            div = response.css('#b_detalii_caracteristici')
            all_lis = div.css('li')  # each li has details about the listing

            # salvam
            tip_proprietate = 'CAS'
            nr_camere = ''
            suprafata = ''
            suprafata_teren = ''
            an = ''
            zona = response.url.split('/')[5].strip('\n')
            pret = response.css('div.pret.first.blue::text').get().replace('.', '').replace(',', '.')

            for li in all_lis:
                titlu = li.css('::text').get()
                val = li.css('span::text').get()

                if 'camere' in titlu:
                    nr_camere = val

                if 'util' in titlu:  # suprafata utila
                    suprafata = val[:-3].replace(',', '.')
                elif suprafata == '' and 'suprafa' in titlu:
                    suprafata = val[:-3].replace(',', '.')

                if 'teren' in titlu:
                    suprafata_teren = val[:-3].replace(',', '.')

                if 'An construc' in titlu or 'an construc' in titlu:
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
                f.write(("can't find nr camere for APT,{},{},{},{},{},{}: "
                         .format(nr_camere, suprafata, suprafata_teren, an, zona, pret) +
                         response.url + "\n").encode('utf-8'))

            if suprafata == '':
                suprafata = '40'
                f.write(("can't find suprafata for APT,{},{},{},{},{},{}: "
                         .format(nr_camere, suprafata, suprafata_teren, an, zona, pret) +
                         response.url + "\n").encode('utf-8'))

            # writing data in CSV
            with open('imobiliare_data.csv', "a") as data:
                writer = csv.writer(data, delimiter=',', quotechar='"', quoting=csv.QUOTE_MINIMAL, lineterminator='\n')
                writer.writerow([tip_proprietate, nr_camere, suprafata, suprafata_teren, an, zona, pret])
