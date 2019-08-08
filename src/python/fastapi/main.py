from fastapi import FastAPI
from pydantic import BaseModel

class Prediction(BaseModel):
    parameters: List[float] = []

app = FastAPI()



@app.get("/")
def read_root():
    return {"Hello": "World"}

@app.post("/predict/")
async def create_predict_item(prediction: Prediction):
    return prediction
