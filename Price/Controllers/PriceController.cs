using Microsoft.AspNetCore.Mvc;

namespace Price.Controllers
{
    [ApiController]
    [Route("price")]
    public class PriceController : ControllerBase
    {
        [HttpGet]
        public Entities.Item getPrice()
        {
            string location = Request.Query["location"];
            Entities.Item item = new Entities.Item();
            item.Tip = Request.Query["tip"];
            item.Compartimentare = Request.Query["compartimentare"];
            item.An = Request.Query["an"];
            item.Etaj = Request.Query["etaj"];
            item.Camere = Request.Query["camere"];
            item.Suprafata = Request.Query["suprafata"];
            
            item.Pret = item.getPrice();
            return item;        
        }
    }
}