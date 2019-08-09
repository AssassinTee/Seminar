# coding: utf-8

from __future__ import absolute_import

from flask import json
from six import BytesIO

from swagger_server.models.data_array import DataArray  # noqa: E501
from swagger_server.models.target import Target  # noqa: E501
from swagger_server.test import BaseTestCase


class TestDefaultController(BaseTestCase):
    """DefaultController integration test stubs"""

    def test_predict_post(self):
        """Test case for predict_post

        
        """
        body = [3.4]
        response = self.client.open(
            '/AssassinTee/Predictor/1.0.0/predict',
            method='POST',
            data=json.dumps(body),
            content_type='application/json')
        self.assert200(response,
                       'Response body is : ' + response.data.decode('utf-8'))

    def test_predictlabel_post(self):
        """Test case for predictlabel_post

        
        """
        body = [3.4]
        response = self.client.open(
            '/AssassinTee/Predictor/1.0.0/predictlabel',
            method='POST',
            data=json.dumps(body),
            content_type='application/json')
        self.assert200(response,
                       'Response body is : ' + response.data.decode('utf-8'))

    def test_train_post(self):
        """Test case for train_post

        
        """
        body = Target()
        response = self.client.open(
            '/AssassinTee/Predictor/1.0.0/train',
            method='POST',
            data=json.dumps(body),
            content_type='application/json')
        self.assert200(response,
                       'Response body is : ' + response.data.decode('utf-8'))


if __name__ == '__main__':
    import unittest
    unittest.main()
