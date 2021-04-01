using System;
using HousingPricePredictionProjectML.Model;
namespace HousingPricePredictionProject
{
    class Program
    {
        // Function for printing the predicted price, given the house's features
        static void showPredictedPrice(string[] features)
        {
            ModelInput sampleData = new ModelInput()
            {
                Tip_proprietate = features[0],
                Nr_camere = int.Parse(features[1]),
                Suprafata = float.Parse(features[2]),
                Suprafata_teren = float.Parse(features[3]),
                An_constructie = int.Parse(features[4]),
                Zona = features[5],
            };

            var predictionResult = ConsumeModel.Predict(sampleData);
            Console.WriteLine($"\n\nPredicted Price: {predictionResult.Score}\n\n");
        }
        static void Main(string[] args)
        {
            string[] newHouse = new string[6] { "CAS", "9", "365", "750", "2004", "centru-civic" };
            showPredictedPrice(newHouse);
        }
    }
}
