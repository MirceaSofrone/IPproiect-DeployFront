// This file was auto-generated by ML.NET Model Builder. 

using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Microsoft.ML;
using HousingPricePredML.Model;

namespace HousingPricePredML.Model
{
    public class ConsumeModel
    {
        private static Lazy<PredictionEngine<ModelInput, ModelOutput>> PredictionEngine = new Lazy<PredictionEngine<ModelInput, ModelOutput>>(CreatePredictionEngine);

        // For more info on consuming ML.NET models, visit https://aka.ms/mlnet-consume
        // Method for consuming model in your app
        public static ModelOutput Predict(ModelInput input)
        {
            ModelOutput result = PredictionEngine.Value.Predict(input);
            return result;
        }

        public static PredictionEngine<ModelInput, ModelOutput> CreatePredictionEngine()
        {
            // Create new MLContext
            MLContext mlContext = new MLContext();

            // Load model & create prediction engine
            string modelPath = @"MLModel.zip";
            Console.WriteLine($"Debug {System.AppDomain.CurrentDomain.BaseDirectory}\n\n");
            ITransformer mlModel = mlContext.Model.Load(modelPath, out var modelInputSchema);
            var predEngine = mlContext.Model.CreatePredictionEngine<ModelInput, ModelOutput>(mlModel);

            return predEngine;
        }


        public static float SendPredictedPrice(ModelInput houseData)
        {
            var predictionResult = ConsumeModel.Predict(houseData);
            float price = predictionResult.Score;
            return price;
        }
    }
}
