using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Logging;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using HousingPricePredML;

namespace WebAPI.Controllers
{
    [ApiController]
    [Route("[controller]")]
    public class PriceController : ControllerBase
    {
        [HttpGet]
        public Output Get(HousingPricePredML.Model.ModelInput InputData)
        {
            Output OutputProperty = new Output()
            {
                TipProprietate = InputData.TipProprietate,
                NrCamere = InputData.NrCamere,
                Suprafata = InputData.Suprafata,
                SuprafataTeren = InputData.SuprafataTeren,
                AnConstructie = InputData.AnConstructie,
                Zona = InputData.Zona,
                Pret = HousingPricePredML.Model.ConsumeModel.SendPredictedPrice(InputData)
            };

            return OutputProperty;
        }
    }
}

