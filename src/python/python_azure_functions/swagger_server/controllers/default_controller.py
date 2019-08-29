import connexion
import six
import numpy as np
import json

from swagger_server.models.data_array import DataArray  # noqa: E501
from swagger_server.models.target import Target  # noqa: E501
from swagger_server import util

from flask import Response

from swagger_server.backends.backend_tf_model import KerasBackend
from swagger_server.backends.backend_mxnet_model import MxnetBackend


model_backend =MxnetBackend()#KerasBackend()

def predict_post(body=None):
    """predict_post

    Returns a prediction-vector of the ML-model

    :param body: 
    :type body: List[]

    :rtype: DataArray
    """
    if(body != None):
        body = np.asarray(body).reshape(1, len(body))
        try:
            res = model_backend.predict(body)
            return res.flatten().tolist()
        except Exception as e:
            print(e)
            return Response("{'message':'wrong body - expects json float list', 'exception':'"+json.dumps(str(e))+"'}", status=400, mimetype='application/json')
            
    return Response("{'message':'no body!'}", status=400, mimetype='application/json')


def predictlabel_post(body=None):  # noqa: E501
    """predictlabel_post

    Returns a prediction label of the ML-model # noqa: E501

    :param body: 
    :type body: List[]

    :rtype: int
    """
    return 'do some magic!'


def train_post(body=None):  # noqa: E501
    """train_post

    Start training the model # noqa: E501

    :param body: 
    :type body: dict | bytes

    :rtype: None
    """
    if connexion.request.is_json:
        body = Target.from_dict(connexion.request.get_json())  # noqa: E501
    return 'do some magic!'
