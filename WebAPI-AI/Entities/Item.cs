namespace Price.Entities
{
    public record Item
    {
        public string Tip { get; init; }
        public int Camere { get; init; }
        public float Suprafata { get; init; }

        public string Compartimentare { get; init; }

        public int An { get; init; }

        public int Etaj { get; init; }

        public float Pret { set; }
    }
}