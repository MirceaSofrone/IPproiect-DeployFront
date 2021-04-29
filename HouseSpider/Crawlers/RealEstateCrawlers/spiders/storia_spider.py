import scrapy
import csv
from RealEstateCrawlers.spiders.common import *


class StoriaSpider(scrapy.Spider):
    name = 'storia'
    start_urls = [
        'https://www.storia.ro/vanzare/apartament/iasi/iasi/iasi/',
        'https://www.storia.ro/vanzare/casa/iasi/iasi/iasi/',
        'https://www.storia.ro/inchiriere/apartament/iasi/iasi/',
        'https://www.storia.ro/inchiriere/casa/iasi/iasi/'
    ]

    def __init__(self, **kwargs):
        super().__init__(**kwargs)
        self.page_no = 0
        self.MAX_NO_OF_PAGES = 2
        self.debug_file = '../../logs/{}_out.txt'.format(self.name)
        self.csv_file = '../../logs/{}_data.csv'.format(self.name)
        init_csv(self.name)

    def parse(self, response, **kwargs):
        self.page_no += 1

        # debug file
        with open(self.debug_file, 'wb') as debug:
            divs = response.css('div.col-md-content.section-listing__row-content article.offer-item')

            for post in divs:
                post_url = post.css('h3 a::attr(href)').get()  # getting link to post
                yield scrapy.Request(post_url, callback=self.parse_listing)

        next_page_url = response.css('li.pager-next a::attr(href)').get()  # works!
        if self.page_no < self.MAX_NO_OF_PAGES and next_page_url is not None:
            yield scrapy.Request(next_page_url.encode('utf-8').decode(), callback=self.parse)

    def parse_listing(self, response):
        with open(self.debug_file, 'ab') as debug:

            # div with all the details of the listing
            details_div = response.css('div.css-1d9dws4.e1dlfs272')

            # saving:
            if 'apartament' in response.css('.css-195qsqd.e1je57sb2')[0].get().lower():
                property_type = 'APT'  # apartment
            else:
                property_type = 'CAS'  # house

            if 'chiriat' in response.css('.css-195qsqd.e1je57sb2')[0].get().lower():
                transaction_type = 'INC'  # renting
            else:
                transaction_type = 'CMP'  # buying

            try:
                zone = response.css('div.css-nc8v92.e9ta1i03 a')[5].css('::text').get().lower().replace(' ', '-')
            except IndexError:
                return

            price = response.css('strong[aria-label=Preț]::text').get()[:-2].replace(' ', '')

            pairs = {}
            for div in details_div.css('div[role=region]'):
                title = div.css('::attr(aria-label)').get()
                value = div.css('.css-1ytkscc.ecjfvbm0::attr(title)').get()
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
