using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Logging;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using HousesFinalScoreML;

namespace WebAPI.Controllers
{
    [ApiController]
    [Route("[controller]")]
    public class PriceController : ControllerBase
    {
        [HttpGet]
        public Output Get(HousesFinalScoreML.Model.ModelInput InputData)
        {
            float price_generated = HousesFinalScoreML.Model.ConsumeModel.SendPredictedPrice(InputData);
            Output OutputProperty = new Output()
            {
                tip_proprietate = InputData.tip_proprietate,
                nr_camere = InputData.nr_camere,
                suprafata = InputData.suprafata,
                suprafata_teren = InputData.suprafata_teren,
                an_constructie = InputData.an_constructie,
                zona = InputData.zona,
                pret_vanzare = price_generated,
                pret_chirie = 0.0065F* price_generated
            };

            return OutputProperty;
        }
    }
}

