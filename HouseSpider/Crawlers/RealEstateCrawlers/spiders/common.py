import csv

CSV_HEADERS = ['tip_proprietate', 'tip_tranzactie', 'nr_camere', 'suprafata', 'suprafata_teren', 'an_constructie',
               'zona', 'pret', 'link']


def init_csv(name):
    # initializing CSV file
    with open('../../logs/{}_data.csv'.format(name), "w+") as data:
        writer = csv.writer(data, delimiter=',', quotechar='"', quoting=csv.QUOTE_MINIMAL, lineterminator='\n')
        writer.writerow(CSV_HEADERS)


def parse_pairs(pairs):
    # salvam:
    nr_camere = ''
    suprafata = ''
    suprafata_teren = ''
    an = ''
    zona = ''

    for titlu, val in pairs.items():
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

    return zona, nr_camere, suprafata, suprafata_teren, an


def write_to_csv(csv_file, property_type, transaction_type, no_of_rooms, surface, terrain_surface, year, zone, price,
                 link):
    if terrain_surface == '':
        terrain_surface = surface

    if year == '' or no_of_rooms == '' or surface == '' or '+' in no_of_rooms:
        return

    # writing data in CSV
    with open(csv_file, "a") as data:
        writer = csv.writer(data, delimiter=',', quotechar='"', quoting=csv.QUOTE_MINIMAL, lineterminator='\n')
        writer.writerow(
            [property_type, transaction_type, no_of_rooms, surface, terrain_surface, year, zone, price, link])
