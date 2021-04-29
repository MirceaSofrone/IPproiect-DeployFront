import scrapy
import csv
from RealEstateCrawlers.spiders.common import *


class HomezzSpider(scrapy.Spider):
    name = 'homezz'
    start_urls = [
        'https://homezz.ro/anunturi_apartamente_de-vanzare_in-iasi-is.html',
        'https://homezz.ro/anunturi_case-vile_de-vanzare_in-iasi-is.html',
        'https://homezz.ro/anunturi_apartamente_de-inchiriat_in-iasi-is.html',
        'https://homezz.ro/anunturi_case-vile_de-inchiriat_in-iasi-is.html'
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
            posts = response.css('#list_cart_holder a')

            for post in posts:
                post_url = post.css('::attr(href)').get()  # getting link to post
                yield scrapy.Request(post_url, callback=self.parse_listing)

            next_page_url = response.css('.next_page a::attr(href)').get()  # works!
            if self.page_no < self.MAX_NO_OF_PAGES and next_page_url is not None:
                yield scrapy.Request(next_page_url.encode('utf-8').decode(), callback=self.parse)

    def parse_listing(self, response):
        with open(self.debug_file, 'ab') as debug:

            # div with all the details of the listing
            details_div = response.css('#extra-fields')

            # saving:
            if 'apartament' in response.css('#categSelect').get().lower():
                property_type = 'APT'  # apartment
            else:
                property_type = 'CAS'  # house

            if 'chiriere' in response.css('.rounded.transaction_type').get():
                transaction_type = 'INC'  # renting
            else:
                transaction_type = 'CMP'  # buying

            price = response.css('#price::text').get().replace(' ', '').replace('.', '')
            if response.css("#price b::text").get() == 'eur':
                pass  # already in euro
            elif response.css("#price b::text").get() == 'ron':
                price = '%s' % (int(price) // 5)  # RON
            else:
                return  # ???

            pairs = {}
            for div in details_div.css('div'):
                title = div.css('span::text').get()
                value = div.css('h1,h2,h3,h4,h5,h6').css('a::text').get()
                if value is None:
                    value = div.css('h1,h2,h3,h4,h5,h6').css('::text').get()

                if value is None:
                    continue

                pairs[title] = value

            zone, no_of_rooms, surface, terrain_surface, year = parse_pairs(pairs)

            write_to_csv(self.csv_file, property_type, transaction_type, no_of_rooms, surface, terrain_surface, year,
                         zone, price, response.url)
