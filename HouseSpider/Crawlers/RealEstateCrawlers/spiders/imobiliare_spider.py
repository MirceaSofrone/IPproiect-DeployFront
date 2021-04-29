import csv
import scrapy
from RealEstateCrawlers.spiders.common import *


class ImobiliareSpider(scrapy.Spider):
    name = 'imobiliare'
    start_urls = [
        'https://www.imobiliare.ro/vanzare-apartamente/iasi',
        'https://www.imobiliare.ro/vanzare-case-vile/iasi?id=172571550',
        'https://www.imobiliare.ro/inchirieri-apartamente/iasi?id=82493434',
        'https://www.imobiliare.ro/inchirieri-case-vile/iasi?id=82493818'
    ]

    def __init__(self, **kwargs):
        super().__init__(**kwargs)
        super().__init__(**kwargs)
        self.page_no = 0
        self.MAX_NO_OF_PAGES = 2
        self.debug_file = '../../logs/{}_out.txt'.format(self.name)
        self.csv_file = '../../logs/{}_data.csv'.format(self.name)
        init_csv(self.name)

    def parse(self, response, **kwargs):
        self.page_no += 1

        with open(self.debug_file, 'wb') as debug:
            divs = response.xpath('//div[@itemtype]')

            for post in divs:
                url = post.css('a.img-block::attr(href)').get()  # getting link to post
                yield scrapy.Request(url, callback=self.parse_listing)

        next_page_url = response.css('a.inainte.butonpaginare::attr(href)').get()
        if self.page_no < self.MAX_NO_OF_PAGES and next_page_url is not None:
            yield scrapy.Request(next_page_url.encode('utf-8').decode(), callback=self.parse)

    def parse_listing(self, response):
        with open(self.debug_file, 'ab') as debug:
            div = response.css('#b_detalii_caracteristici')
            all_lis = div.css('li')  # each li has details about the listing

            # saving:
            if 'apartament' in response.css('.container-breadcrumbs li')[2].css('span').get().lower():
                property_type = 'APT'  # apartment
            else:
                property_type = 'CAS'  # house

            if 'chiriat' in response.css('.container-breadcrumbs li')[2].css('span').get().lower():
                transaction_type = 'INC'  # renting
            else:
                transaction_type = 'CMP'  # buying

            zone = response.url.split('/')[5].strip('\n')
            price = response.css('div.pret.first.blue::text').get().replace('.', '').replace(' ', '').replace('\n', '')

            pairs = {}
            for li in all_lis:
                title = li.css('::text').get()
                value = li.css('span::text').get()
                pairs[title] = value

            _, no_of_rooms, surface, terrain_surface, year = parse_pairs(pairs)

            if terrain_surface == '':
                terrain_surface = surface

            if year == '' or no_of_rooms == '' or surface == '' or 'multe' in no_of_rooms:
                return

            # writing data in CSV
            with open(self.csv_file, "a") as data:
                writer = csv.writer(data, delimiter=',', quotechar='"', quoting=csv.QUOTE_MINIMAL, lineterminator='\n')
                writer.writerow(
                    [property_type, transaction_type, no_of_rooms, surface, terrain_surface, year, zone, price,
                     response.url])
