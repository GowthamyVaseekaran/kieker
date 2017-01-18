/**
 */
package kieker.analysisteetime.model.analysismodel.type;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see kieker.analysisteetime.model.analysismodel.type.TypeFactory
 * @model kind="package"
 * @generated
 */
public interface TypePackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "type";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "platform:/resource/Kieker/model/analysismodel.ecore/type";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "type";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	TypePackage eINSTANCE = kieker.analysisteetime.model.analysismodel.type.impl.TypePackageImpl.init();

	/**
	 * The meta object id for the '{@link kieker.analysisteetime.model.analysismodel.type.impl.TypeRootImpl <em>Root</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see kieker.analysisteetime.model.analysismodel.type.impl.TypeRootImpl
	 * @see kieker.analysisteetime.model.analysismodel.type.impl.TypePackageImpl#getTypeRoot()
	 * @generated
	 */
	int TYPE_ROOT = 0;

	/**
	 * The feature id for the '<em><b>Component Types</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_ROOT__COMPONENT_TYPES = 0;

	/**
	 * The number of structural features of the '<em>Root</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_ROOT_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Root</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_ROOT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link kieker.analysisteetime.model.analysismodel.type.impl.EStringToComponentTypeMapEntryImpl <em>EString To Component Type Map Entry</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see kieker.analysisteetime.model.analysismodel.type.impl.EStringToComponentTypeMapEntryImpl
	 * @see kieker.analysisteetime.model.analysismodel.type.impl.TypePackageImpl#getEStringToComponentTypeMapEntry()
	 * @generated
	 */
	int ESTRING_TO_COMPONENT_TYPE_MAP_ENTRY = 1;

	/**
	 * The feature id for the '<em><b>Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ESTRING_TO_COMPONENT_TYPE_MAP_ENTRY__KEY = 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ESTRING_TO_COMPONENT_TYPE_MAP_ENTRY__VALUE = 1;

	/**
	 * The number of structural features of the '<em>EString To Component Type Map Entry</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ESTRING_TO_COMPONENT_TYPE_MAP_ENTRY_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>EString To Component Type Map Entry</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ESTRING_TO_COMPONENT_TYPE_MAP_ENTRY_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link kieker.analysisteetime.model.analysismodel.type.impl.ComponentTypeImpl <em>Component Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see kieker.analysisteetime.model.analysismodel.type.impl.ComponentTypeImpl
	 * @see kieker.analysisteetime.model.analysismodel.type.impl.TypePackageImpl#getComponentType()
	 * @generated
	 */
	int COMPONENT_TYPE = 2;

	/**
	 * The feature id for the '<em><b>Signature</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_TYPE__SIGNATURE = 0;

	/**
	 * The feature id for the '<em><b>Provided Operations</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_TYPE__PROVIDED_OPERATIONS = 1;

	/**
	 * The number of structural features of the '<em>Component Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_TYPE_FEATURE_COUNT = 2;

	/**
	 * The operation id for the '<em>Get Type Root</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_TYPE___GET_TYPE_ROOT = 0;

	/**
	 * The number of operations of the '<em>Component Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_TYPE_OPERATION_COUNT = 1;

	/**
	 * The meta object id for the '{@link kieker.analysisteetime.model.analysismodel.type.impl.EStringToOperationTypeMapEntryImpl <em>EString To Operation Type Map Entry</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see kieker.analysisteetime.model.analysismodel.type.impl.EStringToOperationTypeMapEntryImpl
	 * @see kieker.analysisteetime.model.analysismodel.type.impl.TypePackageImpl#getEStringToOperationTypeMapEntry()
	 * @generated
	 */
	int ESTRING_TO_OPERATION_TYPE_MAP_ENTRY = 3;

	/**
	 * The feature id for the '<em><b>Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ESTRING_TO_OPERATION_TYPE_MAP_ENTRY__KEY = 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ESTRING_TO_OPERATION_TYPE_MAP_ENTRY__VALUE = 1;

	/**
	 * The number of structural features of the '<em>EString To Operation Type Map Entry</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ESTRING_TO_OPERATION_TYPE_MAP_ENTRY_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>EString To Operation Type Map Entry</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ESTRING_TO_OPERATION_TYPE_MAP_ENTRY_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link kieker.analysisteetime.model.analysismodel.type.impl.OperationTypeImpl <em>Operation Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see kieker.analysisteetime.model.analysismodel.type.impl.OperationTypeImpl
	 * @see kieker.analysisteetime.model.analysismodel.type.impl.TypePackageImpl#getOperationType()
	 * @generated
	 */
	int OPERATION_TYPE = 4;

	/**
	 * The feature id for the '<em><b>Signature</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_TYPE__SIGNATURE = 0;

	/**
	 * The number of structural features of the '<em>Operation Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_TYPE_FEATURE_COUNT = 1;

	/**
	 * The operation id for the '<em>Get Component Type</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_TYPE___GET_COMPONENT_TYPE = 0;

	/**
	 * The number of operations of the '<em>Operation Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_TYPE_OPERATION_COUNT = 1;


	/**
	 * Returns the meta object for class '{@link kieker.analysisteetime.model.analysismodel.type.TypeRoot <em>Root</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Root</em>'.
	 * @see kieker.analysisteetime.model.analysismodel.type.TypeRoot
	 * @generated
	 */
	EClass getTypeRoot();

	/**
	 * Returns the meta object for the map '{@link kieker.analysisteetime.model.analysismodel.type.TypeRoot#getComponentTypes <em>Component Types</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>Component Types</em>'.
	 * @see kieker.analysisteetime.model.analysismodel.type.TypeRoot#getComponentTypes()
	 * @see #getTypeRoot()
	 * @generated
	 */
	EReference getTypeRoot_ComponentTypes();

	/**
	 * Returns the meta object for class '{@link java.util.Map.Entry <em>EString To Component Type Map Entry</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>EString To Component Type Map Entry</em>'.
	 * @see java.util.Map.Entry
	 * @model keyDataType="org.eclipse.emf.ecore.EString"
	 *        valueType="kieker.analysisteetime.model.analysismodel.type.ComponentType" valueContainment="true"
	 * @generated
	 */
	EClass getEStringToComponentTypeMapEntry();

	/**
	 * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Key</em>'.
	 * @see java.util.Map.Entry
	 * @see #getEStringToComponentTypeMapEntry()
	 * @generated
	 */
	EAttribute getEStringToComponentTypeMapEntry_Key();

	/**
	 * Returns the meta object for the containment reference '{@link java.util.Map.Entry <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Value</em>'.
	 * @see java.util.Map.Entry
	 * @see #getEStringToComponentTypeMapEntry()
	 * @generated
	 */
	EReference getEStringToComponentTypeMapEntry_Value();

	/**
	 * Returns the meta object for class '{@link kieker.analysisteetime.model.analysismodel.type.ComponentType <em>Component Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Component Type</em>'.
	 * @see kieker.analysisteetime.model.analysismodel.type.ComponentType
	 * @generated
	 */
	EClass getComponentType();

	/**
	 * Returns the meta object for the attribute '{@link kieker.analysisteetime.model.analysismodel.type.ComponentType#getSignature <em>Signature</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Signature</em>'.
	 * @see kieker.analysisteetime.model.analysismodel.type.ComponentType#getSignature()
	 * @see #getComponentType()
	 * @generated
	 */
	EAttribute getComponentType_Signature();

	/**
	 * Returns the meta object for the map '{@link kieker.analysisteetime.model.analysismodel.type.ComponentType#getProvidedOperations <em>Provided Operations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>Provided Operations</em>'.
	 * @see kieker.analysisteetime.model.analysismodel.type.ComponentType#getProvidedOperations()
	 * @see #getComponentType()
	 * @generated
	 */
	EReference getComponentType_ProvidedOperations();

	/**
	 * Returns the meta object for the '{@link kieker.analysisteetime.model.analysismodel.type.ComponentType#getTypeRoot() <em>Get Type Root</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Get Type Root</em>' operation.
	 * @see kieker.analysisteetime.model.analysismodel.type.ComponentType#getTypeRoot()
	 * @generated
	 */
	EOperation getComponentType__GetTypeRoot();

	/**
	 * Returns the meta object for class '{@link java.util.Map.Entry <em>EString To Operation Type Map Entry</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>EString To Operation Type Map Entry</em>'.
	 * @see java.util.Map.Entry
	 * @model keyDataType="org.eclipse.emf.ecore.EString"
	 *        valueType="kieker.analysisteetime.model.analysismodel.type.OperationType" valueContainment="true"
	 * @generated
	 */
	EClass getEStringToOperationTypeMapEntry();

	/**
	 * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Key</em>'.
	 * @see java.util.Map.Entry
	 * @see #getEStringToOperationTypeMapEntry()
	 * @generated
	 */
	EAttribute getEStringToOperationTypeMapEntry_Key();

	/**
	 * Returns the meta object for the containment reference '{@link java.util.Map.Entry <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Value</em>'.
	 * @see java.util.Map.Entry
	 * @see #getEStringToOperationTypeMapEntry()
	 * @generated
	 */
	EReference getEStringToOperationTypeMapEntry_Value();

	/**
	 * Returns the meta object for class '{@link kieker.analysisteetime.model.analysismodel.type.OperationType <em>Operation Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Operation Type</em>'.
	 * @see kieker.analysisteetime.model.analysismodel.type.OperationType
	 * @generated
	 */
	EClass getOperationType();

	/**
	 * Returns the meta object for the attribute '{@link kieker.analysisteetime.model.analysismodel.type.OperationType#getSignature <em>Signature</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Signature</em>'.
	 * @see kieker.analysisteetime.model.analysismodel.type.OperationType#getSignature()
	 * @see #getOperationType()
	 * @generated
	 */
	EAttribute getOperationType_Signature();

	/**
	 * Returns the meta object for the '{@link kieker.analysisteetime.model.analysismodel.type.OperationType#getComponentType() <em>Get Component Type</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Get Component Type</em>' operation.
	 * @see kieker.analysisteetime.model.analysismodel.type.OperationType#getComponentType()
	 * @generated
	 */
	EOperation getOperationType__GetComponentType();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	TypeFactory getTypeFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link kieker.analysisteetime.model.analysismodel.type.impl.TypeRootImpl <em>Root</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see kieker.analysisteetime.model.analysismodel.type.impl.TypeRootImpl
		 * @see kieker.analysisteetime.model.analysismodel.type.impl.TypePackageImpl#getTypeRoot()
		 * @generated
		 */
		EClass TYPE_ROOT = eINSTANCE.getTypeRoot();

		/**
		 * The meta object literal for the '<em><b>Component Types</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TYPE_ROOT__COMPONENT_TYPES = eINSTANCE.getTypeRoot_ComponentTypes();

		/**
		 * The meta object literal for the '{@link kieker.analysisteetime.model.analysismodel.type.impl.EStringToComponentTypeMapEntryImpl <em>EString To Component Type Map Entry</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see kieker.analysisteetime.model.analysismodel.type.impl.EStringToComponentTypeMapEntryImpl
		 * @see kieker.analysisteetime.model.analysismodel.type.impl.TypePackageImpl#getEStringToComponentTypeMapEntry()
		 * @generated
		 */
		EClass ESTRING_TO_COMPONENT_TYPE_MAP_ENTRY = eINSTANCE.getEStringToComponentTypeMapEntry();

		/**
		 * The meta object literal for the '<em><b>Key</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ESTRING_TO_COMPONENT_TYPE_MAP_ENTRY__KEY = eINSTANCE.getEStringToComponentTypeMapEntry_Key();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ESTRING_TO_COMPONENT_TYPE_MAP_ENTRY__VALUE = eINSTANCE.getEStringToComponentTypeMapEntry_Value();

		/**
		 * The meta object literal for the '{@link kieker.analysisteetime.model.analysismodel.type.impl.ComponentTypeImpl <em>Component Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see kieker.analysisteetime.model.analysismodel.type.impl.ComponentTypeImpl
		 * @see kieker.analysisteetime.model.analysismodel.type.impl.TypePackageImpl#getComponentType()
		 * @generated
		 */
		EClass COMPONENT_TYPE = eINSTANCE.getComponentType();

		/**
		 * The meta object literal for the '<em><b>Signature</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPONENT_TYPE__SIGNATURE = eINSTANCE.getComponentType_Signature();

		/**
		 * The meta object literal for the '<em><b>Provided Operations</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPONENT_TYPE__PROVIDED_OPERATIONS = eINSTANCE.getComponentType_ProvidedOperations();

		/**
		 * The meta object literal for the '<em><b>Get Type Root</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation COMPONENT_TYPE___GET_TYPE_ROOT = eINSTANCE.getComponentType__GetTypeRoot();

		/**
		 * The meta object literal for the '{@link kieker.analysisteetime.model.analysismodel.type.impl.EStringToOperationTypeMapEntryImpl <em>EString To Operation Type Map Entry</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see kieker.analysisteetime.model.analysismodel.type.impl.EStringToOperationTypeMapEntryImpl
		 * @see kieker.analysisteetime.model.analysismodel.type.impl.TypePackageImpl#getEStringToOperationTypeMapEntry()
		 * @generated
		 */
		EClass ESTRING_TO_OPERATION_TYPE_MAP_ENTRY = eINSTANCE.getEStringToOperationTypeMapEntry();

		/**
		 * The meta object literal for the '<em><b>Key</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ESTRING_TO_OPERATION_TYPE_MAP_ENTRY__KEY = eINSTANCE.getEStringToOperationTypeMapEntry_Key();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ESTRING_TO_OPERATION_TYPE_MAP_ENTRY__VALUE = eINSTANCE.getEStringToOperationTypeMapEntry_Value();

		/**
		 * The meta object literal for the '{@link kieker.analysisteetime.model.analysismodel.type.impl.OperationTypeImpl <em>Operation Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see kieker.analysisteetime.model.analysismodel.type.impl.OperationTypeImpl
		 * @see kieker.analysisteetime.model.analysismodel.type.impl.TypePackageImpl#getOperationType()
		 * @generated
		 */
		EClass OPERATION_TYPE = eINSTANCE.getOperationType();

		/**
		 * The meta object literal for the '<em><b>Signature</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute OPERATION_TYPE__SIGNATURE = eINSTANCE.getOperationType_Signature();

		/**
		 * The meta object literal for the '<em><b>Get Component Type</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation OPERATION_TYPE___GET_COMPONENT_TYPE = eINSTANCE.getOperationType__GetComponentType();

	}

} //TypePackage