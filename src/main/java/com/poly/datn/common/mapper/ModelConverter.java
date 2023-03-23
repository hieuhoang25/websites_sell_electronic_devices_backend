package com.poly.datn.common.mapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.TypeToken;
import org.modelmapper.convention.NameTokenizers;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ModelConverter {

  protected ModelMapper modelMapper;

  public ModelConverter() {
    this.modelMapper = new ModelMapper();
    this.modelMapper.getConfiguration().setSourceNameTokenizer(NameTokenizers.UNDERSCORE)
        .setDestinationNameTokenizer(NameTokenizers.UNDERSCORE).setSkipNullEnabled(true).setAmbiguityIgnored(true);
  }

  /**
   * Maps {@code source} to {@code destination}.
   * <p>
   * Note: {@code destination} object must have default constructor with no
   * arguments
   * </p>
   *
   * @param <S> {@code source} type
   * @param <D> {@code destination} type
   * @param source object to map from
   * @param destinationType type to map to
   * @return new object of {@code destination} type.
   */
  public <D, S> D map(final S source, Class<D> destinationType) {
    return modelMapper.map(source, destinationType);
  }

  /**
   * Maps {@code source} to {@code destination}.
   *
   * @param <S> {@code source} type
   * @param <D> {@code destination} type
   * @param source object to map from
   * @param destination object to map to
   */
  public <D, S> D map(final S source, D destination) {
    modelMapper.map(source, destination);
    return destination;
  }

  /**
   * <p>
   * map {@code list of element} from {@code Collection<S> sourceList} into list
   * of mapped object with {@code D} type by iterating the {@code sourceList}
   * </p>
   *
   * @param sourceList list of object to map from
   * @param destinationType type to map to
   * @param <D> destination type
   * @param <S> {@code sourceList} type
   * @return list of mapped object with {@code D} type.
   */
  public <D, S> List<D> mapAllByIterator(final Collection<S> sourceList, Class<D> destinationType) {
    return sourceList.stream().map(source -> map(source, destinationType))
        .collect(Collectors.toList());
  }

  /**
   * <p>
   * map {@code list of element} from {@code Collection<S> sourceList} into list
   * of mapped object with {@code D} type by {@link TypeToken}
   * </p>
   *
   * @param sourceList list of object to map from
   * @param <D> destination type
   * @param <S> {@code sourceList} type
   * @return list of mapped object with {@code D} type.
   */
  public <D, S> List<D> mapAllByMappingType(final Collection<S> sourceList) {
    return modelMapper.map(sourceList, new TypeToken<List<D>>() {}.getType());
  }

  public <S, D> TypeMap<S, D> getTypeMap(Class<S> sourceClazz, Class<D> destinationClazz ) {
    ModelMapper mapper = new ModelMapper();
    mapper.getConfiguration().setSourceNameTokenizer(NameTokenizers.UNDERSCORE)
        .setDestinationNameTokenizer(NameTokenizers.UNDERSCORE).setSkipNullEnabled(true);
    return mapper.createTypeMap(sourceClazz, destinationClazz);
  }
}
