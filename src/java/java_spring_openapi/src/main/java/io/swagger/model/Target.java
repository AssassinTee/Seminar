package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.model.DataArray;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Data and target label
 */
@ApiModel(description = "Data and target label")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-08-06T13:45:03.308Z[GMT]")
public class Target   {
  @JsonProperty("data")
  private DataArray data = null;

  @JsonProperty("label")
  private Integer label = null;

  public Target data(DataArray data) {
    this.data = data;
    return this;
  }

  /**
   * Get data
   * @return data
  **/
  @ApiModelProperty(value = "")

  @Valid
  public DataArray getData() {
    return data;
  }

  public void setData(DataArray data) {
    this.data = data;
  }

  public Target label(Integer label) {
    this.label = label;
    return this;
  }

  /**
   * Get label
   * @return label
  **/
  @ApiModelProperty(example = "1", value = "")

  public Integer getLabel() {
    return label;
  }

  public void setLabel(Integer label) {
    this.label = label;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Target target = (Target) o;
    return Objects.equals(this.data, target.data) &&
        Objects.equals(this.label, target.label);
  }

  @Override
  public int hashCode() {
    return Objects.hash(data, label);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Target {\n");
    
    sb.append("    data: ").append(toIndentedString(data)).append("\n");
    sb.append("    label: ").append(toIndentedString(label)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
