# coding: utf-8

from __future__ import absolute_import
from datetime import date, datetime  # noqa: F401

from typing import List, Dict  # noqa: F401

from swagger_server.models.base_model_ import Model
from swagger_server.models.data_array import DataArray  # noqa: F401,E501
from swagger_server import util


class Target(Model):
    """NOTE: This class is auto generated by the swagger code generator program.

    Do not edit the class manually.
    """
    def __init__(self, data: DataArray=None, label: int=None):  # noqa: E501
        """Target - a model defined in Swagger

        :param data: The data of this Target.  # noqa: E501
        :type data: DataArray
        :param label: The label of this Target.  # noqa: E501
        :type label: int
        """
        self.swagger_types = {
            'data': DataArray,
            'label': int
        }

        self.attribute_map = {
            'data': 'data',
            'label': 'label'
        }
        self._data = data
        self._label = label

    @classmethod
    def from_dict(cls, dikt) -> 'Target':
        """Returns the dict as a model

        :param dikt: A dict.
        :type: dict
        :return: The Target of this Target.  # noqa: E501
        :rtype: Target
        """
        return util.deserialize_model(dikt, cls)

    @property
    def data(self) -> DataArray:
        """Gets the data of this Target.


        :return: The data of this Target.
        :rtype: DataArray
        """
        return self._data

    @data.setter
    def data(self, data: DataArray):
        """Sets the data of this Target.


        :param data: The data of this Target.
        :type data: DataArray
        """

        self._data = data

    @property
    def label(self) -> int:
        """Gets the label of this Target.


        :return: The label of this Target.
        :rtype: int
        """
        return self._label

    @label.setter
    def label(self, label: int):
        """Sets the label of this Target.


        :param label: The label of this Target.
        :type label: int
        """

        self._label = label
