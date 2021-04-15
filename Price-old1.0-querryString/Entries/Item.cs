namespace Price.Entities
{
    public record Item
    {
        public string Tip { get; set; }
        public string Camere { get; set; }
        public string Suprafata { get; set; }

        public string Compartimentare { get; set; }

        public string An { get; set; }

        public string Etaj { get; set; }

        public string Pret { get; set; }

        public string getPrice()
        {
            return "40000";
        }
    }

}